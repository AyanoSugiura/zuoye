package zhou.zuoye.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zhou.zuoye.dao.ConFileRepository;
import zhou.zuoye.model.ConFile;
import zhou.zuoye.service.ConFileService;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class ConFileServiceImpl extends ServiceImpl<ConFile,Integer> implements ConFileService {
    @Resource
    ConFileRepository conFileRepository;

    @Override
    public List<ConFile> findAllByName(String name) {
        return  conFileRepository.findAllByName(name);
    }

}
