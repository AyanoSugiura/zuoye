package zhou.zuoye.service;

import zhou.zuoye.model.Course;
import zhou.zuoye.model.Tassk;

import java.util.List;

public interface TasskService  extends Service<Tassk,Integer>{

    public List<Tassk> findTassksByTitle(String title);
    public List<Tassk> findTassksByCourse(Course course);
}