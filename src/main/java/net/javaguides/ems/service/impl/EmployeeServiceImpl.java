package net.javaguides.ems.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.ems.dto.EmployeeDto;
import net.javaguides.ems.entity.Employee;
import net.javaguides.ems.exception.RecourceNotFound;
import net.javaguides.ems.repository.EmployeeRepository;
import net.javaguides.ems.service.EmployeeService;
import net.javaguides.ems.wrapper.EmployeeWrapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeWrapper.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeWrapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RecourceNotFound("Employee not found with id: " + id));
        return EmployeeWrapper.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEpmloyees() {
        return employeeRepository.findAll()
                .stream().map(EmployeeWrapper::mapToEmployeeDto)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updatedEmployee(Long id, EmployeeDto employeeDto) {
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new RecourceNotFound("Employee not found with id: " + id));
        existingEmployee.setFirstName(employeeDto.getFirstName());
        existingEmployee.setLastName(employeeDto.getLastName());
        existingEmployee.setEmail(employeeDto.getEmail());
        Employee updatedEmployee = employeeRepository.save(existingEmployee);
        return EmployeeWrapper.mapToEmployeeDto(updatedEmployee);
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.findById(id)
                .orElseThrow(() -> new RecourceNotFound("Employee not found with id: " + id));
        employeeRepository.deleteById(id);
    }
}

