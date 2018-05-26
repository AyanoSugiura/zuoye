package zhou.zuoye.controller;

import org.springframework.web.bind.annotation.*;
import zhou.zuoye.model.User;
import zhou.zuoye.model.statistics.PageAndUsers;
import zhou.zuoye.service.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin")
@CrossOrigin
public class AdminController {
    @Resource
    private UserService userService;
    @Resource
    private TasskService tasskService;
    @Resource
    private CourseService courseService;
    @Resource
    private StudentCourseService studentCourseService;
    @Resource
    private StudentWorkService studentWorkService;

    @PostMapping("/tchNoVerifyCount")
    public Integer tchNoVerifyCount(@RequestParam Integer aid, @RequestParam Integer verify) {
        if (userService.findUserById(aid).getUserlevel() != 2) return null;
        return userService.tchVerifyStatusCount(verify);
    }


    @PostMapping("/tchNoVerifysPage")
    public PageAndUsers tchNoVerifysPage(@RequestParam Integer aid, @RequestParam Integer verify, @RequestParam Integer start, @RequestParam Integer page) {
        if (userService.findUserById(aid).getUserlevel() != 2) return null;
        return new PageAndUsers(userService.tchVerifyStatusPages(verify, start, page),userService.tchVerifyStatusCount(verify));
    }

    @PostMapping("/verifyTeacher")
    public PageAndUsers verifyTeacher(@RequestParam Integer aid, @RequestParam Integer start, @RequestParam Integer page, @RequestParam Integer tid, @RequestParam Integer rootVerify, @RequestParam Integer toVerify) {
        if (userService.findUserById(aid).getUserlevel() != 2) return null;
        User tch = userService.findUserById(tid);
        tch.setVerify(toVerify);
        userService.save(tch);
        return new PageAndUsers(userService.tchVerifyStatusPages(rootVerify, start, page),userService.tchVerifyStatusCount(rootVerify));
    }
}
