package zhou.zuoye.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zhou.zuoye.dao.UserRepository;
import zhou.zuoye.model.User;
import zhou.zuoye.service.UserService;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<User,Integer> implements UserService {
    @Resource
    UserRepository userRepository;

    @Override
    public List<User> findAllByName(String name) {
        return userRepository.findAllByName(name);
    }

    @Override
    public User findByPhone(String phone){
        return userRepository.findByPhone(phone);
    }

    @Override
    public User findUserById(Integer id) {
        return userRepository.findUserById(id);
    }
}
