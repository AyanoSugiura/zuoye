package zhou.zuoye.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zhou.zuoye.model.Course;
import zhou.zuoye.model.StudentCourse;
import zhou.zuoye.model.User;

import java.util.List;

@Repository("studentCourseRepository")
public interface StudentCourseRepository extends JpaRepository<StudentCourse,Integer> {
    public List<StudentCourse> findStudentCoursesByStudentAndVerify(User user,Integer verify);

    public  List<StudentCourse> findStudentCoursesByCourseAndAndVerify(Course course,Integer verify);
}
