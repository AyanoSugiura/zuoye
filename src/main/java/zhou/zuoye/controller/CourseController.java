package zhou.zuoye.controller;

import org.springframework.web.bind.annotation.*;
import zhou.zuoye.model.Course;
import zhou.zuoye.model.User;
import zhou.zuoye.service.CourseService;
import zhou.zuoye.service.UserService;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/course")
@CrossOrigin
public class CourseController {
    @Resource
    private CourseService courseService;
    @Resource
    private UserService userService;

    @PostMapping("/save")
    public String save(@RequestParam String name, @RequestParam Integer tid) {
        Course course=new Course(name,userService.findById(tid));
        Course course1 = courseService.save(course);
        if(course1==null)
            return "保存失败";
        else return "保存成功";
    }

    @GetMapping("/findall")
    public List<Course> findAll() {
        List<Course> courses = courseService.findAll();
        return courses;
    }

    @PostMapping("/findcbyu")
    public List<Course> TeacherCourse(@RequestParam Integer tid) {
        User teacher=userService.findById(tid);
        List<Course> courses = courseService.findCoursesByTeacher(teacher);
        return courses;
    }
}
