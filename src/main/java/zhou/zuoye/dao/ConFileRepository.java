package zhou.zuoye.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zhou.zuoye.model.ConFile;

import java.util.List;

@Repository("conFileRepository")

public interface ConFileRepository  extends JpaRepository<ConFile,Integer> {
    public List<ConFile> findAllByName(String name);
}
