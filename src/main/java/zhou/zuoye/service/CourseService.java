package zhou.zuoye.service;

import zhou.zuoye.model.Course;
import zhou.zuoye.model.User;

import java.util.List;

public interface CourseService extends Service<Course,Integer>{

    public Course findByName(String name);
    public List<Course> findCoursesByTeacher(User teacher);
}

