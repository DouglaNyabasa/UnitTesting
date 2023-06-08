package com.example.integrationtestingemployeecrud.controller;
import com.example.integrationtestingemployeecrud.model.Employee;
import com.example.integrationtestingemployeecrud.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/getAll")
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getAllEmployee(){
       return employeeRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public Optional<Employee> getEmployeeById(@PathVariable(value = "id")Long id){
        return employeeRepository.findById(id);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deleteEmployeeById(@PathVariable(value = "id") Long id) throws ChangeSetPersister.NotFoundException{
        if (!employeeRepository.findById(id).isPresent()){
            throw new ChangeSetPersister.NotFoundException();
        }
        employeeRepository.deleteById(id);

        }


    @PostMapping("/add")
    @ResponseStatus(HttpStatus.OK)
    public Employee createEmployee(@RequestBody Employee employee){
       return employeeRepository.save(employee);
    }

    @PutMapping("/update/{id}")
    public Employee updateEmployee(@RequestBody Employee employee) throws ChangeSetPersister.NotFoundException {
       if(employee == null || employee.getId() == null){
           throw new ChangeSetPersister.NotFoundException();
       }
       Optional<Employee> optionalEmployee = employeeRepository.findById(employee.getId());
       if(!optionalEmployee.isPresent()){
           throw new ChangeSetPersister.NotFoundException();
       }
       Employee employee1 = optionalEmployee.get();
       employee1.setFirstname(employee1.getFirstname());
       employee1.setLastName(employee1.getLastName());
       employee1.setDateOfBirth(employee1.getDateOfBirth());
       employee1.setPhoneNumber(employee1.getPhoneNumber());
       employee1.setEmail(employee1.getEmail());
        return employeeRepository.save(employee);
    }


}

