package empdb.Controllers;

import empdb.DB.DataBaseService;
import empdb.DB.Emp;
import empdb.ErrorHandlers.EmployeeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RController {
    @Autowired
    DataBaseService db;

    @GetMapping("/home")
    public String home(){
        return "hello from the server";
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/findAll")
    @ResponseBody  Iterable<Emp> findall(){
        return db.findAll();
    }

    @GetMapping("/findByName/{name}")
    List<Emp> findbyname(@PathVariable String name){
        return db.findByName(name);
    }
    @GetMapping("/findById/{id}")
    Emp findbyid(@PathVariable Long id){
        return db.findById(id);
    }

    @PostMapping("/insert")
    String insert(@RequestBody Emp emp){
        db.insert(emp);
        return "Inserted";
    }

    @PutMapping("/update/{id}")
     String update(@PathVariable Long id, @RequestBody Emp emp){
        Boolean stat=db.update(id,emp);
        if (stat) //means true
            return "Record Updated";
        else
            throw new EmployeeNotFoundException("Could not find employee{id:"+id+"} so record not updated");
    }

    @DeleteMapping("/delete/{id}")
    String delete(@PathVariable Long id){
        Boolean stat=db.delete(id);
        if (stat) //means true
            return "Record Deleted";
        else
            throw new EmployeeNotFoundException("Could not find employee{id:"+id+"} so record could not be deleted");
    }
}
