package zhou.zuoye.service;

import zhou.zuoye.model.StudentWork;
import zhou.zuoye.model.Tassk;
import zhou.zuoye.model.User;

import java.util.List;

public interface StudentWorkService  extends Service<StudentWork,Integer>{

    public List<StudentWork> findStudentWorksByTitle(String title);
    public List<StudentWork> findStudentWorksByTassk(Tassk tassk);
    public List<StudentWork> findStudentWorksByStudent(User student);

    public List<StudentWork> findStudentWorksByTasskAndIsPg(Tassk tassk,Integer isPg);

    public StudentWork findStudentWorkByTasskAndStudent(Tassk tassk,User student);
    public StudentWork findStudentWorkById(Integer id);

    public List<StudentWork> findStudentWorksByStudentAndIsPg(User student,Integer isPg);

    public Integer deleteAllByStudent(User student);

    public Integer deleteAllByStudentAndCourse(Integer sid,Integer cid);

    public Integer deleteCourseStudentWorks(Integer cid);

}
