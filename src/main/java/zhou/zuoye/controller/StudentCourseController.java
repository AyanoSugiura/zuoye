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
        System.out.println("sid： " + sid + " cid: " + cid);
        User student = userService.findById(sid);
        Course course = courseService.findById(cid);
        StudentCourse studentCourse = studentCourseService.findByStudentAndCourse(student, course);
        if (studentCourse != null) return "你已经选了该门课";
        StudentCourse studentCourse1 = new StudentCourse(student, course);
        StudentCourse studentCourse2 = studentCourseService.save(studentCourse1);
        if (studentCourse2 != null)
            return "申请成功待审核";
        else return "申请失败";
    }

    //审核并返回状态
    @PostMapping("/verify")
    public List<StudentCourse> verify(@RequestParam Integer sid, @RequestParam Integer cid, @RequestParam Integer rootVerify,@RequestParam Integer toVerify) {
        User student = new User(sid);
        Course course = new Course(cid);
        StudentCourse studentCourse = studentCourseService.findByStudentAndCourse(student, course);
        studentCourse.setVerify(toVerify);
        studentCourseService.save(studentCourse);
        return studentCourseService.findStudentCoursesByCourseAndVerify(course, rootVerify);
    }

    //未审核
    @PostMapping("/xkv")
    public List<StudentCourse> stuApplyCourseVerify(@RequestParam Integer cid, @RequestParam Integer verify) {
        Course course = new Course(cid);
        List<StudentCourse> studentCourses = studentCourseService.findStudentCoursesByCourseAndVerify(course, verify);
        return studentCourses;
    }

    @PostMapping("/stucourses")
    public List<StudentCourse> studentCourses(@RequestParam Integer sid) {
        User student = new User(sid);
        List<StudentCourse> studentcourses = studentCourseService.findStudentCoursesByStudentAndVerifyNotOrderByVerifyDesc(student,0);
        return studentcourses;
    }

    @PostMapping("/stuisc")
    public String stuIsC(@RequestParam Integer cid, @RequestParam Integer sid) {
        User student = userService.findById(sid);
        Course course = courseService.findCourseById(cid);
        if (course == null) return "课号错误,请检查";
        StudentCourse studentCourse = studentCourseService.findByStudentAndCourse(student, course);
        if (studentCourse != null) return "你已经选了该门课";
        else return "你未选该门课";
    }

    //成员界面用
    @PostMapping("/member")
    public List<StudentCourse> courseMember(@RequestParam Integer cid, @RequestParam Integer verify) {
        Course course = new Course(cid);
        List<StudentCourse> studentCourses = studentCourseService.findStudentCoursesByCourseAndVerify(course, verify);
        return studentCourses;
    }

    /*@PostMapping("/stucourses2")
    public List<StudentCourse> studentCourses2(@RequestParam Integer sid) {
        System.out.println(sid);
        User student=userService.findById(sid);
        List<StudentCourse> courses=studentCourseService.studentCoursesss2(student);
        return courses;
    }*/
}
