package net.javaguides.ems.service;

import net.javaguides.ems.dto.EmployeeDto;
import net.javaguides.ems.entity.Employee;

import java.util.List;

public interface EmployeeService {
    EmployeeDto createEmployee(EmployeeDto employeeDto);
    EmployeeDto getEmployeeById(Long id);
    List<EmployeeDto> getAllEpmloyees();
    EmployeeDto updatedEmployee(Long id, EmployeeDto employeeDto);
    void deleteEmployee(Long id);
}
