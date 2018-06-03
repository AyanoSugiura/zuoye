package zhou.zuoye.service;

import zhou.zuoye.model.Course;
import zhou.zuoye.model.StudentCourse;
import zhou.zuoye.model.User;

import java.util.List;

public interface StudentCourseService extends Service<StudentCourse,Integer>{

    public List<StudentCourse> findStudentCoursesByStudentAndVerify(User user, Integer verify);

    public  List<StudentCourse> findStudentCoursesByCourseAndVerify(Course course, Integer verify);

    public StudentCourse findByStudentAndCourse(User student,Course course);

    //public List<Course> studentCourses(Integer sid);

    public List<StudentCourse> findStudentCoursesByStudent(User student);

    public List<StudentCourse> findStudentCoursesByStudentAndVerifyNotOrderByVerifyDesc(User student,Integer verify);

    public Integer CourseMemberVerifyCount(Integer cid,Integer verify);
    public List<StudentCourse> CourseMemberVerifyPages(Integer cid,Integer verify,Integer start,Integer page);

    public Integer deleteAllByStudentAndCourse(User student,Course course);
    public  Integer deleteAllByCourse(Course course);

}

