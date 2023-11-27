package com.demoEmployee.restApi.repository;

import com.demoEmployee.restApi.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository  extends JpaRepository<Employee, Long> {

}
