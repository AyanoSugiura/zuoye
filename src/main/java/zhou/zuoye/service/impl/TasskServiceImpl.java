package zhou.zuoye.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zhou.zuoye.dao.TasskRepository;
import zhou.zuoye.model.Course;
import zhou.zuoye.model.Tassk;
import zhou.zuoye.service.TasskService;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class TasskServiceImpl extends ServiceImpl<Tassk,Integer> implements TasskService {
    @Resource
    TasskRepository tasskRepository;

    @Override
    public List<Tassk> findTassksByTitle(String title){
        return tasskRepository.findTassksByTitle(title);
    }

    @Override
    public List<Tassk> findTassksByCourse(Course course){
        return tasskRepository.findTassksByCourse(course);
    }

}
