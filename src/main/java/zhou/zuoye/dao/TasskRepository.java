package zhou.zuoye.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zhou.zuoye.model.Course;
import zhou.zuoye.model.Tassk;

import java.util.List;

@Repository("tasskRepository")
public interface TasskRepository extends JpaRepository<Tassk,Integer> {
    public List<Tassk> findTassksByTitle(String title);
    public List<Tassk> findTassksByCourse(Course course);
}