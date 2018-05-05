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
        List<User> isSub = new ArrayList<>();
        List<User> sSub = new ArrayList<>();
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
    public StudentWork subZy(@RequestParam Integer stuId, @RequestParam Integer taskId, @RequestParam String content, @RequestParam String files_links) {
        User student = new User(stuId);
        Tassk tassk = new Tassk(taskId);
        StudentWork studentWork = studentWorkService.findStudentWorkByTasskAndStudent(tassk, student);
        if (studentWork != null && studentWork.getIsPg() == 0) {
            studentWork.setContent(content);
            studentWork.setFiles_links(files_links);
            StudentWork resub = studentWorkService.save(studentWork);
            if (resub == null) return null;
            else {
                resub.setSubStatus("已重新提交");
                return resub;
            }
        }
        if (studentWork != null && studentWork.getIsPg() == 1) {
            studentWork.setSubStatus("已批改，不能提交");
            return studentWork;
        }
        StudentWork studentWork1 = new StudentWork(content, student, tassk, files_links);
        StudentWork studentWork2 = studentWorkService.save(studentWork1);
        if (studentWork2 == null) return null;
        else {
            studentWork2.setSubStatus("提交成功");
            return studentWork2;
        }
    }


    @PostMapping("/flielist")
    public String flieList(@RequestParam Integer stuId, @RequestParam Integer taskId) {
        User student = new User(stuId);
        Tassk tassk = new Tassk(taskId);
        StudentWork studentWork = studentWorkService.findStudentWorkByTasskAndStudent(tassk, student);
        if (studentWork == null) return null;
        else return studentWork.getFiles_links();
    }

    @PostMapping("/stuzy")
    public StudentWork stuZy(@RequestParam Integer stuId, @RequestParam Integer taskId) {
        User student = new User(stuId);
        Tassk tassk = new Tassk(taskId);
        StudentWork studentWork = studentWorkService.findStudentWorkByTasskAndStudent(tassk, student);
        return studentWork;
    }

    @PostMapping("/tchpg")
    public StudentWork tchPg(@RequestParam Integer id, @RequestParam String comment, @RequestParam String score,@RequestParam Integer isPg) {
        StudentWork studentWork= new StudentWork(id);
        StudentWork tStudentWork = studentWorkService.findStudentWorkById(id);
        tStudentWork.setComment(comment);
        tStudentWork.setScore(score);
        tStudentWork.setIsPg(isPg);
        StudentWork returnStudentWork =studentWorkService.save(tStudentWork);
        return returnStudentWork;
    }
}
