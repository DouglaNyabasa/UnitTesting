//package com.example.integrationtestingemployeecrud.service;
//
//
//import com.example.integrationtestingemployeecrud.dto.EmployeeRequestDTO;
//import com.example.integrationtestingemployeecrud.dto.EmployeeResponseDTO;
//import com.example.integrationtestingemployeecrud.model.Employee;
//import com.example.integrationtestingemployeecrud.repository.EmployeeRepository;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class EmployeeService {
//    private EmployeeRepository employeeRepository;
//
//    public EmployeeService(EmployeeRepository employeeRepository) {
//        this.employeeRepository = employeeRepository;
//    }
//
//    public List<Employee> getAllEmployee(){
//       return employeeRepository.findAll();
//
//    }
//    private EmployeeResponseDTO mapToEmployeeResponse(Employee employee) {
//        return EmployeeResponseDTO.builder()
//                .id(employee.getId())
//                .firstname(employee.getFirstname())
//                .lastName(employee.getLastName())
//                .phoneNumber(employee.getPhoneNumber())
//                .dateOfBirth(employee.getDateOfBirth())
//                .email(employee.getEmail())
//                .build();
//    }
//    public ResponseEntity createEmployee(EmployeeRequestDTO employeeRequestDTO){
//        Employee employee = Employee.builder()
//                .firstname(employeeRequestDTO.getFirstname())
//                .lastName(employeeRequestDTO.getLastName())
//                .dateOfBirth(employeeRequestDTO.getDateOfBirth())
//                .email(employeeRequestDTO.getEmail())
//                .phoneNumber(employeeRequestDTO.getPhoneNumber())
//                .build();
//        employeeRepository.save(employee);
//        return ResponseEntity.ok().body(employee);
//
//    }
//    public ResponseEntity updateEmployee(Long id, EmployeeRequestDTO employeeRequestDTO){
//        Optional<Employee> employee = employeeRepository.findById(id);
//        if (employee.isPresent()){
//            Employee employee1 = employee.get();
//            employee1.setFirstname(employeeRequestDTO.getFirstname());
//            employee1.setLastName(employeeRequestDTO.getLastName());
//            employee1.setDateOfBirth(employeeRequestDTO.getDateOfBirth());
//            employee1.setEmail(employeeRequestDTO.getEmail());
//            employee1.setPhoneNumber(employeeRequestDTO.getPhoneNumber());
//            employeeRepository.save(employee1);
//            return ResponseEntity.ok().body("Employee details has been updated");
//        }
//        else
//            return ResponseEntity.ok().body("Employee Not Found");
//    }
//    public ResponseEntity deleteEmployee(Long id){
//        Optional<Employee> employee = employeeRepository.findById(id);
//        if (employee.isPresent()){
//            employeeRepository.delete(employee.get());
//            return ResponseEntity.ok().body("Employee successfully deleted");
//        }
//        else
//            return ResponseEntity.ok().body("Employee Not Found");
//    }
//}
