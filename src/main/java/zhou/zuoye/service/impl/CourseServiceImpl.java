package zhou.zuoye.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zhou.zuoye.dao.CourseRepository;
import zhou.zuoye.model.Course;
import zhou.zuoye.model.Tassk;
import zhou.zuoye.model.User;
import zhou.zuoye.service.CourseService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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

    @Override
    public Course findCourseByTeacherAndName(User teacher,String name){
        return courseRepository.findCourseByTeacherAndName(teacher,name);
    }
    @Override
    public Course findCourseById(Integer id){
        return courseRepository.findCourseById(id);
    }

    @Override
    public Integer CourseMemberTotal(Integer cid) {
        return courseRepository.CourseMemberTotal(cid);
    }

    @Override
    public Tassk courseRecentTriTask(Integer cid){
        return courseRepository.courseRecentTriTask(cid);
    }
}
