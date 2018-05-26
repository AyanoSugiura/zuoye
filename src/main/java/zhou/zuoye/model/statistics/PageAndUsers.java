package zhou.zuoye.model.statistics;

import zhou.zuoye.model.User;

import java.util.List;

public class PageAndUsers {
    List<User> users;
    Integer page ;

    public PageAndUsers(){}

    public PageAndUsers(List<User> users) {
        this.users = users;
    }

    public PageAndUsers(Integer page) {
        this.page = page;
    }

    public PageAndUsers(List<User> users, Integer page) {
        this.users = users;
        this.page = page;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }
}
