package zhou.zuoye.service;

import zhou.zuoye.model.ConFile;

import java.util.List;

public interface ConFileService extends Service<ConFile,Integer>{

    public List<ConFile> findAllByName(String name);
}
