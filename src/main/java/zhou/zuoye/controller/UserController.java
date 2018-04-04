package zhou.zuoye.controller;

import org.springframework.web.bind.annotation.*;
import zhou.zuoye.model.User;
import zhou.zuoye.service.UserService;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("/login")
    public User login(@RequestParam String phone, @RequestParam String password) {
        User user = userService.findByPhone(phone);
        if (user != null && user.getPassword().equals(password)) {
            user.setPassword("");
            return user;
        } else return null;
    }

    @PostMapping("/register")
    public User studentRegister(User user) {
        userService.save(user);
        return user;
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

