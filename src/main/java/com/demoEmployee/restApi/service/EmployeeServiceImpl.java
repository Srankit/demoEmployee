package com.demoEmployee.restApi.service;


import com.demoEmployee.restApi.employeeDto.EmployeeDto;
import com.demoEmployee.restApi.entity.Employee;
import com.demoEmployee.restApi.repository.EmployeeRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService{

@Autowired
 private EmployeeRepository employeeRepository;





    private Employee mapToEntity(EmployeeDto employeeDto){
        Employee emp = new Employee();

        emp.setName(employeeDto.getName());
        emp.setEmail(employeeDto.getEmail());
        emp.setDepartment(employeeDto.getDepartment());
        emp.setJoiningDate(LocalDate.parse(employeeDto.getJoiningDate()));
        return emp;
    }


    private  EmployeeDto mapToDto(Employee employee){
        EmployeeDto dto = new EmployeeDto();
        dto.setId(employee.getId());
        dto.setName(employee.getName());
        dto.setEmail(employee.getEmail());
        dto.setDepartment(employee.getDepartment());
        dto.setJoiningDate(String.valueOf(employee.getJoiningDate()));
        return dto;
    }

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee= mapToEntity(employeeDto);
        Employee newEmp= employeeRepository.save(employee);
        return mapToDto(newEmp);
    }

    @Override
    public List<EmployeeDto> gelAllEmployee() {
        List<Employee> employees= employeeRepository.findAll();

        return employees.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public EmployeeDto getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found"+ id));
        return mapToDto(employee);
    }

    @Override
    public EmployeeDto updateEmployeeById(EmployeeDto employeeDto, Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found"+ id));
        employee.setName(employeeDto.getName());
        employee.setEmail(employeeDto.getEmail());
        employee.setDepartment(employeeDto.getDepartment());
        employee.setJoiningDate(LocalDate.parse(employeeDto.getJoiningDate()));
        Employee updateEmp = employeeRepository.save(employee);
        return mapToDto(updateEmp);
    }

    @Override
    public void deleteEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found"+ id));

        employeeRepository.deleteById(id);
    }
}
