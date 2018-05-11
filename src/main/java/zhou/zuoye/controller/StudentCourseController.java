package zhou.zuoye.controller;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import zhou.zuoye.model.Course;
import zhou.zuoye.model.StudentCourse;
import zhou.zuoye.model.User;
import zhou.zuoye.service.CourseService;
import zhou.zuoye.service.StudentCourseService;
import zhou.zuoye.service.UserService;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
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
    public List<StudentCourse> verify(@RequestParam Integer sid, @RequestParam Integer cid, @RequestParam Integer rootVerify, @RequestParam Integer toVerify) {
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
        List<StudentCourse> studentcourses = studentCourseService.findStudentCoursesByStudentAndVerifyNotOrderByVerifyDesc(student, 0);
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


    //成员界面用 excel增加成员用
    @PostMapping("/exceladdmember")
    public List<StudentCourse> excelAddMember(@RequestParam("file") MultipartFile file, @RequestParam Integer cid) throws IOException {
        System.out.println(file.getOriginalFilename());
        System.out.println(cid);

        Course course = courseService.findCourseById(cid);
        String flieType = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));

        User student;
        StudentCourse studentCourse;

        String phone;
        String name;

        System.out.println(flieType);

        if (flieType.equals(".xlsx")) {
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(file.getInputStream());
            XSSFSheet sheet = xssfWorkbook.getSheetAt(0);
            for (Iterator ite = sheet.rowIterator(); ite.hasNext(); ) {
                XSSFRow row = (XSSFRow) ite.next();
                phone = Integer.toString((int) (row.getCell(0).getNumericCellValue()));
                name = row.getCell(1).toString();

                student = userService.findByPhone(phone);
                if (student == null) {
                    student = new User(name, "123456", phone);
                    student = userService.save(student);
                }
                if (studentCourseService.findByStudentAndCourse(student,course)==null) {
                    studentCourse = new StudentCourse(student, course, 2);
                    studentCourseService.save(studentCourse);
                }
            }
        } else if (flieType.equals(".xls")) {

        }

        return courseMember(cid, 2);
    }
    /*@PostMapping("/stucourses2")
    public List<StudentCourse> studentCourses2(@RequestParam Integer sid) {
        System.out.println(sid);
        User student=userService.findById(sid);
        List<StudentCourse> courses=studentCourseService.studentCoursesss2(student);
        return courses;
    }*/
}
