package zhou.zuoye.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zhou.zuoye.model.Course;
import zhou.zuoye.model.User;

import java.util.List;

@Repository("courseRepository")
public interface CourseRepository extends JpaRepository<Course,Integer> {
    public Course findByName(String name);
    public List<Course> findCoursesByTeacher(User teacher);
    public Course findCourseByTeacherAndName(User teacher,String name);
    public Course findCourseById(Integer id);
}
