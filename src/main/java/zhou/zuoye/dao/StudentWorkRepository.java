package zhou.zuoye.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zhou.zuoye.model.StudentWork;
import zhou.zuoye.model.Tassk;
import zhou.zuoye.model.User;

import java.util.List;

@Repository("studentWorkRepository")
public interface StudentWorkRepository extends JpaRepository<StudentWork,Integer> {
    public List<StudentWork> findStudentWorksByTitle(String title);
    public List<StudentWork> findStudentWorksByTassk(Tassk tassk);
    public List<StudentWork> findStudentWorksByStudent(User student);

    public List<StudentWork> findStudentWorksByTasskAndIsPg(Tassk tassk,Integer isPg);

    public StudentWork findStudentWorkByTasskAndStudent(Tassk tassk,User student);

    public StudentWork findStudentWorkById(Integer id);
}

