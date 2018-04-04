package zhou.zuoye.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zhou.zuoye.dao.StudentWorkFileRepository;
import zhou.zuoye.model.ConFile;
import zhou.zuoye.model.StudentWork;
import zhou.zuoye.model.StudentWorkFile;
import zhou.zuoye.service.StudentWorkFileService;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class StudentWorkFileServiceImpl extends ServiceImpl<StudentWorkFile,Integer> implements StudentWorkFileService {
    @Resource
    StudentWorkFileRepository studentWorkFileRepository;

    @Override
    public List<StudentWorkFile> findStudentWorkFilesByStudentWork(StudentWork studentWork){
        return studentWorkFileRepository.findStudentWorkFilesByStudentWork(studentWork);
    }

    @Override
    public List<StudentWorkFile> findStudentWorkFilesByFile(ConFile file){
        return studentWorkFileRepository.findStudentWorkFilesByFile(file);
    }
}