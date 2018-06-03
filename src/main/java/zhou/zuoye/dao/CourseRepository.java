package zhou.zuoye.dao;

import com.github.pagehelper.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import zhou.zuoye.model.Course;
import zhou.zuoye.model.Tassk;
import zhou.zuoye.model.User;

import java.util.List;
import java.util.Map;

@Repository("courseRepository")
public interface CourseRepository extends JpaRepository<Course,Integer> {
    public Course findByName(String name);
    public List<Course> findCoursesByTeacher(User teacher);
    public Course findCourseByTeacherAndName(User teacher,String name);
    public Course findCourseById(Integer id);

    @Query(value = "SELECT COUNT(*) FROM student_course WHERE cid=?1 AND verify=2" ,nativeQuery=true)
    public Integer CourseMemberTotal(Integer cid);
    @Query(value = "SELECT * FROM tassk WHERE cid=?1 AND verify=2 AND usable=1 ORDER BY id DESC LIMIT 1 " ,nativeQuery=true)
    public Tassk courseRecentTriTask(Integer cid);

}
