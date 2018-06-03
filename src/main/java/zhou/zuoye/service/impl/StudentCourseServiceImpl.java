package zhou.zuoye.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zhou.zuoye.dao.StudentCourseRepository;
import zhou.zuoye.model.Course;
import zhou.zuoye.model.StudentCourse;
import zhou.zuoye.model.User;
import zhou.zuoye.service.StudentCourseService;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class StudentCourseServiceImpl extends ServiceImpl<StudentCourse,Integer> implements StudentCourseService {
    @Resource
    StudentCourseRepository studentCourseRepository;

    @Override
    public List<StudentCourse> findStudentCoursesByStudentAndVerify(User user, Integer verify){
       return studentCourseRepository.findStudentCoursesByStudentAndVerify(user, verify);
    }

    @Override
    public  List<StudentCourse> findStudentCoursesByCourseAndVerify(Course course, Integer verify){
        return  studentCourseRepository.findStudentCoursesByCourseAndVerify(course, verify);
    }

   /* @Override
    public List<Course> studentCourses(Integer sid){
        return  studentCourseRepository.studentCourses(sid);
    }

    @Override
    public List<StudentCourse> studentCoursesss2(User user){
        return studentCourseRepository.studentCoursesss2(user);
    }*/

    @Override
    public StudentCourse findByStudentAndCourse(User student,Course course){
        return studentCourseRepository.findByStudentAndCourse(student,course);
    }

    @Override
    public List<StudentCourse> findStudentCoursesByStudent(User student){
        return  studentCourseRepository.findStudentCoursesByStudent(student);
    }

    @Override
    public List<StudentCourse> findStudentCoursesByStudentAndVerifyNotOrderByVerifyDesc(User student, Integer verify) {
        return studentCourseRepository.findStudentCoursesByStudentAndVerifyNotOrderByVerifyDesc(student, verify);
    }

    @Override
    public Integer CourseMemberVerifyCount(Integer cid, Integer verify) {
        return studentCourseRepository.CourseMemberVerifyCount(cid, verify);
    }

    @Override
    public List<StudentCourse> CourseMemberVerifyPages(Integer cid, Integer verify, Integer start, Integer page) {
        return studentCourseRepository.CourseMemberVerifyPages(cid, verify, start, page);
    }

    @Override
    public Integer deleteAllByStudentAndCourse(User student, Course course) {
        return studentCourseRepository.deleteAllByStudentAndCourse(student, course);
    }

    @Override
    public Integer deleteAllByCourse(Course course) {
        return studentCourseRepository.deleteAllByCourse(course);
    }

}