package com.demoEmployee.restApi.service;

import com.demoEmployee.restApi.employeeDto.EmployeeDto;
import com.demoEmployee.restApi.entity.Employee;
import com.demoEmployee.restApi.repository.EmployeeRepository;
import jakarta.validation.ConstraintViolationException;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class EmployeeServiceTest {


    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    public void testCreateEmployee() {
        EmployeeDto employeeDto = createEmployeeDto();
        when(employeeRepository.save(any(Employee.class))).thenReturn(createEmployee());

        EmployeeDto savedEmployee = employeeService.createEmployee(employeeDto);

        assertNotNull(savedEmployee);
        assertEquals(employeeDto.getName(), savedEmployee.getName());
        assertEquals(employeeDto.getEmail(), savedEmployee.getEmail());
        assertEquals(employeeDto.getDepartment(), savedEmployee.getDepartment());
        assertEquals(employeeDto.getJoiningDate(), savedEmployee.getJoiningDate());
    }

    @Test
    public void testCreateEmployee_InvalidEmail() {
        MockitoAnnotations.openMocks(this);  // Initialize the mocks

        EmployeeDto employeeDto = createEmployeeDtoWithInvalidEmail();

        when(employeeRepository.save(any(Employee.class))).thenThrow(ConstraintViolationException.class);

        assertThrows(ConstraintViolationException.class, () -> employeeService.createEmployee(employeeDto));

        verify(employeeRepository, never()).save(any(Employee.class));
    }

    private EmployeeDto createEmployeeDtoWithInvalidEmail() {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName("John Doe");
        employeeDto.setEmail("invalidEmail");
        employeeDto.setDepartment("IT");
        employeeDto.setJoiningDate("2023-01-01");
        return employeeDto;
    }

    private EmployeeDto createEmployeeDto() {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName("John Doe");
        employeeDto.setEmail("john.doe@example.com");
        employeeDto.setDepartment("IT");
        employeeDto.setJoiningDate("2023-01-01");
        return employeeDto;
    }

    private Employee createEmployee() {
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("John Doe");
        employee.setEmail("john.doe@example.com");
        employee.setDepartment("IT");
        employee.setJoiningDate(LocalDate.parse("2023-01-01"));
        return employee;
    }
}
