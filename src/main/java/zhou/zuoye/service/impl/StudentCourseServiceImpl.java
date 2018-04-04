package zhou.zuoye.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zhou.zuoye.dao.StudentCourseRepository;
import zhou.zuoye.model.Course;
import zhou.zuoye.model.StudentCourse;
import zhou.zuoye.model.User;
import zhou.zuoye.service.StudentCourseService;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class StudentCourseServiceImpl extends ServiceImpl<StudentCourse,Integer> implements StudentCourseService {
    @Resource
    StudentCourseRepository studentCourseRepository;

    @Override
    public List<StudentCourse> findStudentCoursesByStudentAndVerify(User user, Integer verify){
       return studentCourseRepository.findStudentCoursesByStudentAndVerify(user, verify);
    }

    @Override
    public  List<StudentCourse> findStudentCoursesByCourseAndAndVerify(Course course, Integer verify){
        return  studentCourseRepository.findStudentCoursesByCourseAndAndVerify(course, verify);
    }
}