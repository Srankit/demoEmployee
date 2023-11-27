package com.demoEmployee.restApi.employeeDto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
    private Long id;
    private  String name;
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String email;
    private String department;
    private  String joiningDate;


}
