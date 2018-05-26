package zhou.zuoye.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import zhou.zuoye.model.User;

import java.util.List;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User,Integer> {
    public List<User> findAllByName(String name);
    public User findByPhone(String phone);
    public User findUserById(Integer id);


    @Query(value = "SELECT COUNT(*) FROM user WHERE userlevel=1 AND verify=?1 AND usable=1" ,nativeQuery=true)
    public Integer tchVerifyStatusCount(Integer verify);

    @Query(value = "SELECT * FROM user WHERE userlevel=1 AND verify=?1 AND usable=1 LIMIT ?2,?3" ,nativeQuery=true)
    public List<User> tchVerifyStatusPages(Integer verify,Integer start,Integer page);
}
