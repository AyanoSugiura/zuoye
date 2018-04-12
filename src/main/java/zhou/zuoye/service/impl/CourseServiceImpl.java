package zhou.zuoye.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zhou.zuoye.dao.CourseRepository;
import zhou.zuoye.model.Course;
import zhou.zuoye.model.User;
import zhou.zuoye.service.CourseService;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class CourseServiceImpl extends ServiceImpl<Course,Integer> implements CourseService {
    @Resource
    CourseRepository courseRepository;

    @Override
    public Course findByName(String name) {
        return  courseRepository.findByName(name);
    }

    @Override
    public List<Course> findCoursesByTeacher(User teacher){
        return  courseRepository.findCoursesByTeacher(teacher);
    }

}
