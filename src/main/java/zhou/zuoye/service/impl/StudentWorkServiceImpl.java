package zhou.zuoye.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zhou.zuoye.dao.StudentWorkRepository;
import zhou.zuoye.model.StudentWork;
import zhou.zuoye.model.Tassk;
import zhou.zuoye.model.User;
import zhou.zuoye.service.StudentWorkService;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class StudentWorkServiceImpl extends ServiceImpl<StudentWork,Integer> implements StudentWorkService {
    @Resource
    StudentWorkRepository studentWorkRepository;

    @Override
    public List<StudentWork> findStudentWorksByTitle(String title){
        return studentWorkRepository.findStudentWorksByTitle(title);
    }

    @Override
    public List<StudentWork> findStudentWorksByTassk(Tassk tassk){
        return studentWorkRepository.findStudentWorksByTassk(tassk);
    }

    @Override
    public List<StudentWork> findStudentWorksByStudent(User student){
        return studentWorkRepository.findStudentWorksByStudent(student);
    }

    @Override
    public List<StudentWork> findStudentWorksByTasskAndIsPg(Tassk tassk,Integer isPg){
        return  studentWorkRepository.findStudentWorksByTasskAndIsPg(tassk,isPg);
    }

    @Override
    public StudentWork findStudentWorkByTasskAndStudent(Tassk tassk,User student){
        return studentWorkRepository.findStudentWorkByTasskAndStudent(tassk, student);
    }
}
