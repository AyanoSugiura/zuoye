package zhou.zuoye.service;

import zhou.zuoye.model.Course;
import zhou.zuoye.model.StudentCourse;
import zhou.zuoye.model.User;

import java.util.List;

public interface StudentCourseService extends Service<StudentCourse,Integer>{

    public List<StudentCourse> findStudentCoursesByStudentAndVerify(User user, Integer verify);

    public  List<StudentCourse> findStudentCoursesByCourseAndAndVerify(Course course, Integer verify);
}

