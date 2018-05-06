package zhou.zuoye.controller;

import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import zhou.zuoye.model.*;
import zhou.zuoye.service.CourseService;
import zhou.zuoye.service.StudentCourseService;
import zhou.zuoye.service.StudentWorkService;
import zhou.zuoye.service.TasskService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/task")
@CrossOrigin
public class TasskController {
    @Resource
    private TasskService tasskService;
    @Resource
    private CourseService courseService;
    @Resource
    private StudentCourseService studentCourseService;
    @Resource
    private StudentWorkService studentWorkService;

    @PostMapping("/add")
    public String add(@RequestParam Integer cid, @RequestParam String files_links, @RequestParam String title, @RequestParam String content) {
        Tassk tassk = new Tassk(courseService.findById(cid), title, content, files_links);
        System.out.println(cid);
        System.out.println(title);
        System.out.println(content);
        System.out.println(files_links);
        Tassk tassk1 = tasskService.save(tassk);
        if (tassk1 == null)
            return "发布失败";
        else return "发布成功";
    }

    @PostMapping("/taskbc")
    public List<Tassk> teacherTasks(@RequestParam Integer cid) {
        Course course = new Course(cid);
        List<Tassk> tassks = tasskService.findTassksByCourse(course);
        return tassks;
    }

    @PostMapping("/stutasks")
    public String studentTasks(@RequestParam Integer tasskId, @RequestParam Integer uid) {
        User student = new User(uid);
        Tassk tassk = new Tassk(tasskId);
        StudentWork studentWork = studentWorkService.findStudentWorkByTasskAndStudent(tassk, student);
        if (studentWork == null) return "未提交";
        else if (studentWork.getIsPg() == 0) return "未批改";
        else return "已批改";
    }

    @PostMapping("/stunewzy")
    public List<Tassk> stuNewZy(@RequestParam Integer sid) {
        User student = new User(sid);

        List<StudentCourse> studentCourses = studentCourseService.findStudentCoursesByStudentAndVerify(student, 1);
        List<Tassk> tassks = tasskService.findAll(Sort.by(Sort.Order.desc("id")));
        List<Tassk> rtTassks = new ArrayList<>();

        for (Tassk tassk : tassks) {
            for (StudentCourse studentCourse : studentCourses) {
                if(tassk.getCourse().getId()==studentCourse.getCourse().getId()){
                    tassk.setIsSub(studentTasks(tassk.getId(),sid));
                    rtTassks.add(tassk);
                    break;
                }
            }
        }

        return tassks;
    }
}
