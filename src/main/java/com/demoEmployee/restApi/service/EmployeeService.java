package com.demoEmployee.restApi.service;

import com.demoEmployee.restApi.employeeDto.EmployeeDto;
import com.demoEmployee.restApi.entity.Employee;

import java.util.List;

public interface EmployeeService {
  EmployeeDto createEmployee(EmployeeDto employeeDto);
    List<EmployeeDto> gelAllEmployee();
  EmployeeDto getEmployeeById( Long id);
  EmployeeDto updateEmployeeById(EmployeeDto employeeDto,Long id);

    void deleteEmployeeById(Long id);


}
