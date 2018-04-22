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

    @PostMapping("/tchcourses")
    public List<Course> teacherCourses(@RequestParam Integer tid) {
        User teacher=userService.findById(tid);
        if (teacher==null||teacher.getPhone()==null) return  null;
        List<Course> courses = courseService.findCoursesByTeacher(teacher);
        return courses;
    }

    @PostMapping("/tchcoursesbp")
    public List<Course> teacherCourseByphone(@RequestParam String phone) {
        User teacher=userService.findByPhone(phone);
        if (teacher==null||teacher.getPhone()==null) return  null;
        List<Course> courses = courseService.findCoursesByTeacher(teacher);
        return courses;
    }

    @PostMapping("/jcnnamebt")
    public String jcNmaeByTecher(@RequestParam String name, @RequestParam Integer tid) {
        Course course = courseService.findCourseByTeacherAndName(userService.findById(tid),name);
        if (course != null&& name.equals(course.getName()))
            return "该课程名已存在";
        else return "该课程名可用";
    }

}
