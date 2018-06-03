package zhou.zuoye.dao;

import org.springframework.data.annotation.Transient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
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
    public List<StudentWork> findStudentWorksByStudentAndIsPg(User student,Integer isPg);

    public List<StudentWork> findStudentWorksByTasskAndIsPg(Tassk tassk,Integer isPg);

    public StudentWork findStudentWorkByTasskAndStudent(Tassk tassk,User student);

    public StudentWork findStudentWorkById(Integer id);

    public Integer deleteAllByStudent(User student);

    @Transient
    @Modifying
    @Query(value = "DELETE student_work.* from student_work where sid=?1 and student_work.tsk_id in (select tassk.id from tassk where tassk.cid=?2)",nativeQuery=true)
    public Integer deleteAllByStudentAndCourse(Integer sid,Integer cid);

    @Transient
    @Modifying
    @Query(value = "DELETE student_work.* from student_work where tsk_id in (select tassk.id from tassk where cid=?1)",nativeQuery=true)
    public Integer deleteCourseStudentWorks(Integer cid);

}

