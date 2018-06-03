package zhou.zuoye.service;

import zhou.zuoye.model.Course;
import zhou.zuoye.model.Tassk;
import zhou.zuoye.model.User;

import java.util.List;
import java.util.Map;

public interface CourseService extends Service<Course,Integer>{

    public Course findByName(String name);
    public List<Course> findCoursesByTeacher(User teacher);
    public Course findCourseByTeacherAndName(User teacher,String name);
    public Course findCourseById(Integer id);
    public Integer CourseMemberTotal(Integer cid);
    public Tassk courseRecentTriTask(Integer cid);

}

