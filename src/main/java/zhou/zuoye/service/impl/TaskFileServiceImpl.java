package zhou.zuoye.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zhou.zuoye.dao.TaskFileRepository;
import zhou.zuoye.model.ConFile;
import zhou.zuoye.model.TaskFile;
import zhou.zuoye.model.Tassk;
import zhou.zuoye.service.TaskFileService;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class TaskFileServiceImpl extends ServiceImpl<TaskFile,Integer> implements TaskFileService {
    @Resource
    TaskFileRepository taskFileRepository;

    @Override
    public List<TaskFile> findTaskFilesByTassk(Tassk tassk){
        return  taskFileRepository.findTaskFilesByTassk(tassk);
    }

    @Override
    public List<TaskFile> findTaskFilesByFile(ConFile file){
        return taskFileRepository.findTaskFilesByFile(file);
    }
}
