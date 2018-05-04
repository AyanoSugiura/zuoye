package zhou.zuoye.controller;

import org.springframework.web.bind.annotation.*;
import zhou.zuoye.model.*;
import zhou.zuoye.service.StudentCourseService;
import zhou.zuoye.service.StudentWorkService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/szy")
@CrossOrigin
public class StudentWorkController {
    @Resource
    private StudentWorkService studentWorkService;
    @Resource
    private StudentCourseService studentCourseService;

    @PostMapping("/ispg")
    public List<StudentWork> isPg(@RequestParam Integer taskId, @RequestParam Integer isPg) {
        Tassk tassk = new Tassk(taskId);
        List<StudentWork> studentWorks = studentWorkService.findStudentWorksByTasskAndIsPg(tassk, isPg);
        return studentWorks;
    }

    @PostMapping("/unsub")
    public List<User> unSub(@RequestParam Integer taskId, @RequestParam Integer courseId) {
        Tassk tassk = new Tassk(taskId);
        Course course = new Course(courseId);
        List<StudentWork> studentWorks = studentWorkService.findStudentWorksByTassk(tassk);
        List<StudentCourse> studentCourses = studentCourseService.findStudentCoursesByCourseAndAndVerify(course, 1);
        List<User> isSub =new ArrayList<>();
        List<User> sSub =new ArrayList<>();
        for (StudentCourse xk : studentCourses) {
           sSub.add(xk.getStudent());
        }

        for (StudentWork zy : studentWorks) {
            isSub.add(zy.getStudent());
        }

        sSub.removeAll(isSub);
        return sSub;
    }

    @PostMapping("/subzy")
    public String subZy(){
        return null;
    }

    @PostMapping("/flielist")
    public String flieList(@RequestParam Integer stuId, @RequestParam Integer taskId){
        User student =new User(stuId);
        Tassk tassk=new Tassk(taskId);
        StudentWork studentWork= studentWorkService.findStudentWorkByTasskAndStudent(tassk,student);
        if (studentWork==null) return null;
        else return studentWork.getFiles_links();
    }
}
