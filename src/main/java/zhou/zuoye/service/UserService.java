package zhou.zuoye.service;


import zhou.zuoye.model.User;

import java.util.List;

public interface UserService extends Service<User,Integer>{

    public List<User> findAllByName(String name);
    public User findByPhone(String phone);
    public User findUserById(Integer id);

    public Integer tchVerifyStatusCount(Integer verify);
    public List<User> tchVerifyStatusPages(Integer verify,Integer start,Integer page);

    public Integer userVerifyStatusCount();
    public List<User> userVerifyStatusPages(Integer start,Integer page);


}
