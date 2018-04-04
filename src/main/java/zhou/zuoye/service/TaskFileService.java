package zhou.zuoye.service;

import zhou.zuoye.model.ConFile;
import zhou.zuoye.model.TaskFile;
import zhou.zuoye.model.Tassk;

import java.util.List;

public interface TaskFileService  extends Service<TaskFile,Integer>{

    public List<TaskFile> findTaskFilesByTassk(Tassk tassk);
    public List<TaskFile> findTaskFilesByFile(ConFile file);
}
