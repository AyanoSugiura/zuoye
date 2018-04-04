package zhou.zuoye.service;

import zhou.zuoye.model.Course;

import java.util.List;

public interface CourseService extends Service<Course,Integer>{

    public Course findByName(String name);
}

