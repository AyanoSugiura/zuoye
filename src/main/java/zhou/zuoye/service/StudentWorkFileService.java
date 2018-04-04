package zhou.zuoye.service;

import zhou.zuoye.model.ConFile;
import zhou.zuoye.model.StudentWork;
import zhou.zuoye.model.StudentWorkFile;

import java.util.List;

public interface StudentWorkFileService extends Service<StudentWorkFile,Integer>{

    public List<StudentWorkFile> findStudentWorkFilesByStudentWork(StudentWork studentWork);
    public List<StudentWorkFile> findStudentWorkFilesByFile(ConFile file);
}
