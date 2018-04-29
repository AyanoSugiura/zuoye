package zhou.zuoye.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import zhou.zuoye.model.Course;
import zhou.zuoye.model.Tassk;
import zhou.zuoye.service.CourseService;
import zhou.zuoye.service.TasskService;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/task")
@CrossOrigin
public class TasskController {
    @Resource
    private TasskService tasskService;
    @Resource
    private CourseService courseService;

    @PostMapping("/add")
    public String add(@RequestParam Integer cid,@RequestParam String files_links,@RequestParam String title,@RequestParam String content) {
        Tassk tassk =new Tassk(courseService.findById(cid),title,content,files_links);
        System.out.println(cid);
        System.out.println(title);
        System.out.println(content);
        System.out.println(files_links);
        Tassk tassk1 =tasskService.save(tassk);
        if(tassk1==null)
            return "发布失败";
        else return "发布成功";
    }

    @PostMapping("/taskbc")
    public List<Tassk> teacherCourseByphone(@RequestParam Integer cid) {
        Course course =new Course(cid);
        List<Tassk> tassks = tasskService.findTassksByCourse(course);

        return tassks;
    }

}
