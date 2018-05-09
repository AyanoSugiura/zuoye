package zhou.zuoye.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import zhou.zuoye.model.Course;
import zhou.zuoye.model.StudentCourse;
import zhou.zuoye.model.User;

import java.util.List;

@Repository("studentCourseRepository")
public interface StudentCourseRepository extends JpaRepository<StudentCourse,Integer> {
    public List<StudentCourse> findStudentCoursesByStudentAndVerify(User user,Integer verify);
    public List<StudentCourse> findStudentCoursesByCourseAndVerify(Course course,Integer verify);
    public StudentCourse findByStudentAndCourse(User student,Course course);

    @Query("select kc from Course kc , StudentCourse zy where kc.teacher =:tid and zy.student=:sid and kc.id <> zy.course")
    public List<Course> findSCnotC(@Param("tid") Integer tid,@Param("sid") Integer sid);

    @Query("select kc from Course kc , StudentCourse sc where sc.student =:sid and kc.id =sc.course")
    public List<Course> studentCourses(@Param("sid") Integer sid);

    @Query("select sc from StudentCourse sc where sc.student =:sid")
    public List<StudentCourse> studentCoursesss2(@Param("sid") User user);
}
