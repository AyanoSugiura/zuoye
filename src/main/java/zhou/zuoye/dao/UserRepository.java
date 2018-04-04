package zhou.zuoye.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zhou.zuoye.model.User;

import java.util.List;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User,Integer> {
    public List<User> findAllByName(String name);
    public User findByPhone(String phone);
}
