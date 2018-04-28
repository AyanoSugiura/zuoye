package zhou.zuoye.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/files")
@CrossOrigin
public class FilesController {
    @PostMapping("/save")
    public String save( @RequestParam("file") MultipartFile file) {
        try {
            System.out.println(file.getOriginalFilename());
            file.transferTo(new File("D:\\zhou1\\projects\\zuoyefiles\\" + file.getOriginalFilename()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  "http://localhost:8080/files/get/"+file.getOriginalFilename();
    }
}
