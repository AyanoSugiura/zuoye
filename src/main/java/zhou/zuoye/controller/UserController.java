package zhou.zuoye.controller;

import org.springframework.web.bind.annotation.*;
import zhou.zuoye.model.Course;
import zhou.zuoye.model.User;
import zhou.zuoye.service.UserService;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    @Resource
    private UserService userService;


    @PostMapping("/login")
    public Object login(@RequestParam String phone, @RequestParam String password) {
        User user = userService.findByPhone(phone);
        if(user==null) return null;
        if(user.getUsable()==0) return "该账号已停用";
        if (user.getVerify()==0) return "该账号未通过审核";
        if(user.getUserlevel()==1&&user.getVerify()==1)return "该教师账号已注册，请等待管理员审核后方可登陆";
        if (user != null && user.getPassword().equals(password)) {
            user.setPassword("");
            return user;
        } else return null;
    }

    @PostMapping("/register")
    public String userRegister(User user) {
        User user0 = userService.findByPhone(user.getPhone());
        if (user0 == null) {
            User user1 = userService.save(user);
            if (user1 == null)
                return "注册失败";
            else return "注册成功";
        } else return "该账号已被注册";
    }

    @PostMapping("/jcphone")
    public String jcPhone(String phone) {
        User user = userService.findByPhone(phone);
        if (user != null)
            return "该号码已注册";
        else return "该号码未注册";
    }

    @PostMapping("/save/user")
    public User save(User user) {
        userService.save(user);
        return user;
    }


    @PostMapping("/delete")
    public void delete(@RequestParam Integer id) {
        userService.deleteById(id);
    }

    @PostMapping("/detailbyid")
    public User detailById(@RequestParam Integer id) {
        User user = userService.findById(id);
        return user;
    }


    @GetMapping("/findall")
    public List<User> findAll() {
        List<User> users = userService.findAll();
        return users;
    }

    @PostMapping("/usermsg")
    public User getUserById(@RequestParam Integer id) {
        User user = userService.findUserById(id);
        System.out.println(user.getPhone());
        return user;
    }

    @PostMapping("/altermsg")
    public User alterMsg(@RequestParam Integer uid, @RequestParam String name, @RequestParam String phone) {
        User user = userService.findUserById(uid);
        user.setName(name);
        user.setPhone(phone);
        user = userService.save(user);
        return user;
    }

    @PostMapping("/alterpsw")
    public String alterPsw(@RequestParam Integer uid, @RequestParam String password, @RequestParam String newpsw) {
        User user = userService.findUserById(uid);
        if (password.equals(user.getPassword())){
            user.setPassword(newpsw);
            user=userService.save(user);
            if (user!=null) return "密码更改成功";
            else return "密码更改失败";
        }else return "当前密码错误";
    }
}

    /*

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        User user = userService.findById(id);
        return ResultGenerator.genSuccessResult(user);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<User> list = userService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }*/

