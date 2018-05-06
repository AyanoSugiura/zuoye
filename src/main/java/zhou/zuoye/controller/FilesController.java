package zhou.zuoye.controller;

import com.github.pagehelper.Page;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public String save( @RequestParam("file") MultipartFile file) {
        try {
            file.transferTo(new File("D:\\zhou1\\projects\\zuoyefiles\\" + file.getOriginalFilename()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  "http://localhost:8089/files/get/"+file.getOriginalFilename();
    }

    @GetMapping("/get/{filename:.+}")
    public ResponseEntity get( @PathVariable String filename) throws UnsupportedEncodingException {
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
}
