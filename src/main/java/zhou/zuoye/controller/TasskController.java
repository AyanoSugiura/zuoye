package zhou.zuoye.controller;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import zhou.zuoye.model.*;
import zhou.zuoye.model.statistics.PgStatistics;
import zhou.zuoye.model.statistics.TchStatistic;
import zhou.zuoye.service.CourseService;
import zhou.zuoye.service.StudentCourseService;
import zhou.zuoye.service.StudentWorkService;
import zhou.zuoye.service.TasskService;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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

    //用于课程统计界面
    @PostMapping("/tchstatistic")
    List<TchStatistic> tchStatistic(@RequestParam Integer cid) {
        Course course = new Course(cid);
        List<StudentCourse> studentCs = studentCourseService.findStudentCoursesByCourseAndVerify(course, 2);
        List<Tassk> tassks = tasskService.findTassksByCourse(course);
        if (tassks.size() == 0) return null;

        TchStatistic tchStatistic;
        List<TchStatistic> tchStatistics = new ArrayList<>();
        List<StudentWork> studentWorks;

        StudentWork studentWork;

        File file = new File("D:\\zhou1\\projects\\zuoyefiles\\output\\tchstatistic\\tchstatistic.xlsx");
        if (!file.exists()) {   //文件不存在则创建文件，先创建目录
            File dir = new File(file.getParent());
            dir.mkdirs();
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet();
        int i = 0;

        for (StudentCourse studentCourse : studentCs) {
            int j = 2;
            XSSFRow row = sheet.createRow(i);
            studentWorks = new ArrayList<>();

            XSSFCell cellNub = row.createCell(0);
            XSSFCell cellName = row.createCell(1);
            cellNub.setCellValue(studentCourse.getStudent().getId());
            cellName.setCellValue(studentCourse.getStudent().getName());

            for (Tassk tassk : tassks) {
                XSSFCell cell = row.createCell(j);
                studentWork = studentWorkService.findStudentWorkByTasskAndStudent(tassk, studentCourse.getStudent());
                if (studentWork == null) {
                    studentWork = new StudentWork("0 (缺交)");
                    studentWorks.add(studentWork);
                    cell.setCellValue("0 (缺交)");
                } else if (studentWork.getIsPg() == 0 || studentWork.getScore() == "" || studentWork.getScore() == null) {
                    studentWork = new StudentWork("0(未批)");
                    studentWorks.add(studentWork);
                    cell.setCellValue("0 (未批)");
                } else {
                    studentWorks.add(studentWork);
                    cell.setCellValue(studentWork.getScore());
                }
                j = j + 1;
            }
            tchStatistic = new TchStatistic(studentCourse.getStudent(), studentWorks);
            tchStatistics.add(tchStatistic);
            i = i + 1;
        }


        try {
            FileOutputStream fileOut = new FileOutputStream(file);
            workbook.write(fileOut);
            fileOut.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (tchStatistics.size() != 0)
            tchStatistics.get(0).setExcelLink("http://localhost:8089/files/getstatistic/tchstatistic.xlsx");

        return tchStatistics;
    }

    @PostMapping("/stunewzy")
    public List<Tassk> stuNewZy(@RequestParam Integer sid) {
        User student = new User(sid);

        List<StudentCourse> studentCourses = studentCourseService.findStudentCoursesByStudentAndVerify(student, 2);
        List<Tassk> tassks = tasskService.findAll(Sort.by(Sort.Order.desc("id")));
        List<Tassk> rtTassks = new ArrayList<>();

        for (Tassk tassk : tassks) {
            for (StudentCourse studentCourse : studentCourses) {
                if (tassk.getCourse().getId() == studentCourse.getCourse().getId()) {
                    tassk.setIsSub(studentTasks(tassk.getId(), sid));
                    rtTassks.add(tassk);
                    break;
                }
            }
        }
        return rtTassks;
    }

    @PostMapping("/tchnewtsk")
    public List<Tassk> tchNewTsk(@RequestParam Integer tid) {
        User teacher = new User(tid);
        List<Course> courses = courseService.findCoursesByTeacher(teacher);
        List<Tassk> tassks = tasskService.findAll(Sort.by(Sort.Order.desc("id")));
        List<Tassk> rtTassks = new ArrayList<>();

        for (Tassk tassk : tassks) {
            for (Course course : courses) {
                if (tassk.getCourse().getId() == course.getId()) {
                    tassk.setPgStatistics(pgStatisticss(tassk.getId(),tassk.getCourse().getId()));
                    rtTassks.add(tassk);
                    break;
                }
            }
        }
        return rtTassks;
    }






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
        List<StudentCourse> studentCourses = studentCourseService.findStudentCoursesByCourseAndVerify(course, 2);
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

    @PostMapping("/pgstatistics")
    public PgStatistics pgStatisticss(@RequestParam Integer taskId, @RequestParam Integer courseId) {
        List<StudentWork> noPgs = isPg(taskId, 0);
        List<StudentWork> isPgs = isPg(taskId, 1);
        List<User> unsubs = unSub(taskId, courseId);
        PgStatistics pgStatistic = new PgStatistics(isPgs.size(), noPgs.size(), unsubs.size());
        return pgStatistic;
    }

}
