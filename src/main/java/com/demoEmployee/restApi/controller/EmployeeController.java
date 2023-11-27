package com.demoEmployee.restApi.controller;


import com.demoEmployee.restApi.employeeDto.EmployeeDto;
import com.demoEmployee.restApi.service.EmployeeServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@Validated
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeServiceImpl employeeServiceImpl;
    private Long id;

    public EmployeeController(EmployeeServiceImpl employeeServiceImpl) {
        this.employeeServiceImpl = employeeServiceImpl;
    }

    @PostMapping
public ResponseEntity<EmployeeDto> createEmployee(@Valid @RequestBody EmployeeDto employeeDto){
        return  new ResponseEntity<>(employeeServiceImpl.createEmployee(employeeDto), HttpStatus.CREATED);
}

@GetMapping
public List<EmployeeDto> getAllEmployee(){
        return employeeServiceImpl.gelAllEmployee();

}


@GetMapping("/{id}")
public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable (name = "id") Long id){
        return ResponseEntity.ok(employeeServiceImpl.getEmployeeById(id));
    }

    @PutMapping("/{id}")
    public  ResponseEntity<EmployeeDto> updateEmployee(@RequestBody EmployeeDto employeeDto, @PathVariable (value = "id") Long id){
        EmployeeDto empDto= employeeServiceImpl.updateEmployeeById(employeeDto,id);
        return new ResponseEntity<>(empDto,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        employeeServiceImpl.deleteEmployeeById(id);
        return ResponseEntity.ok("Employee entity deleted successfully");
    }
}
