package zhou.zuoye.service;

import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.util.List;

public interface Service<T,TD extends Serializable> {
    <S extends T> S save(S model);//持久化
    //void save(List<T> models);//批量持久化
    void deleteById(Integer id);//通过主鍵刪除
    T findById(Integer id);//通过ID查找
    List<T> findAll();//获取所有
    public List<T> findAll(Sort var1);
}
