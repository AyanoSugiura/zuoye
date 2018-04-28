package zhou.zuoye.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import zhou.zuoye.model.Tassk;
import zhou.zuoye.service.TasskService;

import javax.annotation.Resource;

@RestController
@RequestMapping("/task")
@CrossOrigin
public class TasskController {
    @Resource
    private TasskService tasskService;

    @PostMapping("/add")
    public String add(Tassk tassk) {

        return null;
    }
}
