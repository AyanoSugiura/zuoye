package zhou.zuoye.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import zhou.zuoye.service.Service;

import java.io.Serializable;
import java.util.List;

public class ServiceImpl<T,TD extends Serializable> implements Service<T,TD> {

    @Autowired
    JpaRepository<T,Integer> myppRepository;

    @Override
    public void save(T model) {
        myppRepository.save(model);
    }
    @Override
    public T findById(Integer id) {
        return myppRepository.getOne(id);
    }

    @Override
    public void deleteById(Integer id) {
        myppRepository.deleteById(id);
    }
    @Override
    public List<T> findAll() {
      return (List<T>)myppRepository.findAll();
    }

    @Override
    public List<T> findAll(Sort var1) {
        return (List<T>)myppRepository.findAll( var1);
    }
   /* @Override
    public void save(List models) {

    }



    @Override
    public T findById(Integer id) {
        return null;
    }

    @Override
    public List findAll() {
        return null;
    }*/
}
