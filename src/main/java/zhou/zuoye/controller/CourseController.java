package zhou.zuoye.controller;

import org.springframework.web.bind.annotation.*;
import zhou.zuoye.model.Course;
import zhou.zuoye.model.Tassk;
import zhou.zuoye.model.User;
import zhou.zuoye.service.*;

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
    @Resource
    private TasskService tasskService;
    @Resource
    private StudentWorkService studentWorkService;
    @Resource
    private StudentCourseService studentCourseService;


    @PostMapping("/save")
    public String save(@RequestParam String name, @RequestParam Integer tid) {
        Course course = new Course(name, userService.findById(tid));
        Course course1 = courseService.save(course);
        if (course1 == null)
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
        User teacher = userService.findById(tid);
        if (teacher == null || teacher.getPhone() == null) return null;
        List<Course> courses = courseService.findCoursesByTeacher(teacher);
        return courses;
    }

    @PostMapping("/tchcoursesbp")
    public List<Course> teacherCourseByphone(@RequestParam Integer tid) {
        User teacher = userService.findUserById(tid);
        if (teacher == null || teacher.getPhone() == null) return null;
        List<Course> courses = courseService.findCoursesByTeacher(teacher);
        for (Course course : courses) {
            System.out.println(course.getId());
            course.setStuNum(courseService.CourseMemberTotal(course.getId()));
            Tassk tassk = tasskService.courseRecentTriTask(course.getId());
            if (tassk != null) {
                course.setRecentTriTaskId(tassk.getId());
                course.setRecentTriTaskTitle(tassk.getTitle());
            }
        }
        return courses;
    }

    @PostMapping("/jcnnamebt")
    public String jcNmaeByTecher(@RequestParam String name, @RequestParam Integer tid) {
        Course course = courseService.findCourseByTeacherAndName(userService.findById(tid), name);
        if (course != null && name.equals(course.getName()))
            return "该课程名已存在";
        else return "该课程名可用";
    }

    //成员界面用
    @PostMapping("/findcourse")
    public Course findCourse(@RequestParam Integer cid) {
        return courseService.findCourseById(cid);
    }

    //成员界面用
    @PostMapping("/altername")
    public Course alterName(@RequestParam Integer cid, @RequestParam String name) {
        Course course = courseService.findCourseById(cid);
        course.setName(name);
        return courseService.save(course);
    }

    //课程设置界面评分改百分制用
    @PostMapping("/alterselect")
    public Course alterSelectscr(@RequestParam Integer cid, @RequestParam String selectscr) {
        Course course = courseService.findCourseById(cid);
        course.setSelectscr(selectscr);
        return courseService.save(course);
    }

    //重置或删除课程
    @PostMapping("/refreshOrDeleteCourse")
    public String refreshOrDeleteCourse(@RequestParam Integer cid, @RequestParam String psw, @RequestParam Integer rOrD) {
        Course course = courseService.findCourseById(cid);
        System.out.println(psw);
        System.out.println(course.getTeacher().getPassword());
        if(!(course.getTeacher().getPassword().equals(psw)))return "密码错误";
        studentWorkService.deleteCourseStudentWorks(cid);
        tasskService.deleteTassksByCourse(course);
        studentCourseService.deleteAllByCourse(course);
        if(rOrD==1)courseService.deleteById(cid);
        return rOrD==1?"课程已经删除":(rOrD==2?"课程已经重置":"");
    }
}
