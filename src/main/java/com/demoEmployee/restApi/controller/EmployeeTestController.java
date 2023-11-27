package com.demoEmployee.restApi.controller;

import com.demoEmployee.restApi.employeeDto.EmployeeDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.springframework.http.RequestEntity.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeTestController {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateEmployee() throws Exception {
        EmployeeDto employeeDto = createEmployeeDto();

        MvcResult result = mockMvc.perform((RequestBuilder) post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.valueOf(objectMapper.writeValueAsString(employeeDto))))
                .andExpect(status().isCreated())
                .andReturn();

        // Optionally, you can validate the response body or headers
        // For example, if your endpoint returns the created entity, you can extract and validate it from the result.

        // Employee createdEmployee = objectMapper.readValue(result.getResponse().getContentAsString(), Employee.class);
        // assertNotNull(createdEmployee);
        // assertEquals(employeeDto.getName(), createdEmployee.getName());
        // assertEquals(employeeDto.getEmail(), createdEmployee.getEmail());
    }

    @Test
    public void testCreateEmployee_InvalidEmail() throws Exception {
        EmployeeDto employeeDto = createEmployeeDtoWithInvalidEmail();

        mockMvc.perform((RequestBuilder) post("/api/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.valueOf(objectMapper.writeValueAsString(employeeDto))))  // Use content here
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Email should be valid"));
    }


    // Add more tests for other endpoints as needed

    private EmployeeDto createEmployeeDto() {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName("John Doe");
        employeeDto.setEmail("john.doe@example.com");
        employeeDto.setDepartment("IT");
        employeeDto.setJoiningDate("2023-01-01");
        return employeeDto;
    }

    private EmployeeDto createEmployeeDtoWithInvalidEmail() {
        EmployeeDto employeeDto = createEmployeeDto();
        employeeDto.setEmail("invalidEmail");
        return employeeDto;
    }
}
