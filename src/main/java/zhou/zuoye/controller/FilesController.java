package zhou.zuoye.controller;

import com.github.pagehelper.Page;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import zhou.zuoye.util.PdfConverter;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/files")
@CrossOrigin
public class FilesController {

    @PostMapping("/save")
    public String save(@RequestParam("file") MultipartFile file) {
        try {
            file.transferTo(new File("D:\\zhou1\\projects\\zuoyefiles\\" + file.getOriginalFilename()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "http://localhost:8089/files/get/" + file.getOriginalFilename();
    }

    @GetMapping("/get/{filename:.+}")
    public ResponseEntity get(@PathVariable String filename) throws UnsupportedEncodingException {
        //System.out.println(filename.substring(0,filename.lastIndexOf('.')+1));
        File file = new File("D:\\zhou1\\projects\\zuoyefiles\\" + filename);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Content-Disposition", "attachment; filename=" + URLEncoder.encode(filename, StandardCharsets.UTF_8.toString()));
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentLength(file.length())
                // .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new FileSystemResource(file));
    }

    @GetMapping("/getstatistic/{filename:.+}")
    public ResponseEntity excelStatistic(@PathVariable String filename) throws UnsupportedEncodingException {
        File file = new File("D:\\zhou1\\projects\\zuoyefiles\\output\\tchstatistic\\" + filename);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Content-Disposition", "attachment; filename=" + URLEncoder.encode(filename, StandardCharsets.UTF_8.toString()));
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentLength(file.length())
                // .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new FileSystemResource(file));
    }

    @GetMapping("/getfiletopdf/{filename:.+}")
    public ResponseEntity getfiletopdf(@PathVariable String filename) throws Exception {
        String flieType = filename.substring(filename.lastIndexOf('.'));
        String pdfFlieName=filename.substring(0,filename.lastIndexOf('.')+1)+"pdf";
        String source="D:\\zhou1\\projects\\zuoyefiles\\" + filename;
        String dest="D:\\zhou1\\projects\\zuoyefiles\\zydpdf\\"+pdfFlieName;
        int type = (flieType == ".doc" || flieType == ".docx") ? 0 : ((flieType == ".xls" || flieType == ".xlsx") ? 1 : 2);
        PdfConverter.init(0,source,dest);
        PdfConverter.converter();
        File file = new File(dest);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Content-Disposition", "attachment; filename=" + URLEncoder.encode(pdfFlieName, StandardCharsets.UTF_8.toString()));
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentLength(file.length())
                // .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new FileSystemResource(file));
    }
}
