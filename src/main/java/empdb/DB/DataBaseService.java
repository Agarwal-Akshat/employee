package empdb.DB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataBaseService {
    @Autowired
    EmpRepo repo;

    public void insert(Emp emp){
        repo.save(emp);
    }

    public Iterable<Emp> findAll(){
        return repo.findAll();
    }

    public List<Emp> findByName(String name){
        return repo.findByName(name);
    }

    public Emp findById(Long id){
        return repo.findById(id).get();
    }

    public Boolean update(Long id,Emp emp){
        if (repo.existsById(id)==true) {
            repo.save(emp);
            return true;
        }
        else
            return false;
    }
    public Boolean delete(Long id){
        if (repo.existsById(id)==true) {
            repo.deleteById(id);
            return true;
        }
        else
            return false;
    }

}
