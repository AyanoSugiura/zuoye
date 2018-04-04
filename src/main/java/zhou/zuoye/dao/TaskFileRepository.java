package zhou.zuoye.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zhou.zuoye.model.ConFile;
import zhou.zuoye.model.TaskFile;
import zhou.zuoye.model.Tassk;

import java.util.List;

@Repository("taskFileRepository")
public interface TaskFileRepository extends JpaRepository<TaskFile,Integer> {
    public List<TaskFile> findTaskFilesByTassk(Tassk tassk);
    public List<TaskFile> findTaskFilesByFile(ConFile file);

}

