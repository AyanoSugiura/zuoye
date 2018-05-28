package zhou.zuoye.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import zhou.zuoye.model.ConFile;
import zhou.zuoye.model.StudentWork;
import zhou.zuoye.model.StudentWorkFile;
import zhou.zuoye.model.User;

import java.util.List;

@Repository("studentWorkFileRepository")
public interface StudentWorkFileRepository  extends JpaRepository<StudentWorkFile,Integer> {
    public List<StudentWorkFile> findStudentWorkFilesByStudentWork(StudentWork studentWork);
    public List<StudentWorkFile> findStudentWorkFilesByFile(ConFile file);
}