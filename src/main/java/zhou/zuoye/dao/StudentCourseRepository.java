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
    public List<StudentCourse> findStudentCoursesByStudentAndVerifyNotOrderByVerifyDesc(User student,Integer verify);
    public List<StudentCourse> findStudentCoursesByStudent(User student);
    public StudentCourse findByStudentAndCourse(User student,Course course);

    @Query(value = "SELECT COUNT(*) FROM student_course WHERE cid=?1 AND verify=?2" ,nativeQuery=true)
    public Integer CourseMemberVerifyCount(Integer cid,Integer verify);

    @Query(value = "SELECT * FROM student_course WHERE cid=?1 AND verify=?2 LIMIT ?3,?4" ,nativeQuery=true)
    public List<StudentCourse> CourseMemberVerifyPages(Integer cid,Integer verify,Integer start,Integer page);


    public Integer deleteAllByStudentAndCourse(User student,Course course);
}
