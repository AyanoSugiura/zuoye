package zhou.zuoye.controller;

import org.springframework.web.bind.annotation.*;
import zhou.zuoye.model.Course;
import zhou.zuoye.model.StudentCourse;
import zhou.zuoye.model.User;
import zhou.zuoye.service.CourseService;
import zhou.zuoye.service.StudentCourseService;
import zhou.zuoye.service.UserService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/xk")
@CrossOrigin
public class StudentCourseController {
    @Resource
    private CourseService courseService;
    @Resource
    private UserService userService;
    @Resource
    private StudentCourseService studentCourseService;

    @PostMapping("/apply")
    public String apply(@RequestParam Integer sid, @RequestParam Integer cid) {
        User student = userService.findById(sid);
        Course course= courseService.findById(cid);
        StudentCourse studentCourse = studentCourseService.findByStudentAndCourse(student,course);
        if (studentCourse !=null||studentCourse.getCourse().getName()!=null) return "你已经选了该门课";
        StudentCourse studentCourse1 = new StudentCourse(student,course);
        StudentCourse studentCourse2 = studentCourseService.save(studentCourse1);
        if(studentCourse2 !=null)
            return "申请成功待审核";
        else return "申请失败";
    }

    @PostMapping("/stucourses")
    public List<Course> studentCourses(@RequestParam Integer sid) {
        User student=userService.findById(sid);
        if(student==null||student.getPhone()==null) return null;
        List<StudentCourse> studentcourses = studentCourseService.findStudentCoursesByStudentAndVerify(student,1);
        if(studentcourses==null||studentcourses.get(0)==null) return null;
        System.out.println(studentcourses.get(0).getCourse().getName());
        List<Course> courses=new ArrayList<>();
        for (StudentCourse sc:studentcourses) {
            courses.add(sc.getCourse());
        }
        return courses;
    }

    /*@PostMapping("/stucourses2")
    public List<StudentCourse> studentCourses2(@RequestParam Integer sid) {
        System.out.println(sid);
        User student=userService.findById(sid);
        List<StudentCourse> courses=studentCourseService.studentCoursesss2(student);
        return courses;
    }*/
}
