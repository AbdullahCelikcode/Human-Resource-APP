package api.humanresource.service.impl;

import api.humanresource.model.entity.EmployeeEntity;
import api.humanresource.model.request.Employee.EmployeeCreateRequest;
import api.humanresource.model.request.Employee.EmployeePasswordUpdateRequest;
import api.humanresource.model.request.Employee.EmployeeUpdateRequest;
import api.humanresource.model.response.EmployeesResponse;
import api.humanresource.repository.EmployeeRepository;
import api.humanresource.service.EmployeeEmailService;
import api.humanresource.service.EmployeeService;
import api.humanresource.util.exception.GlobalException;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeEmailService employeeEmailService;
    private final PasswordEncoder passwordEncoder;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeEmailService employeeEmailService, PasswordEncoder passwordEncoder) {
        this.employeeRepository = employeeRepository;
        this.employeeEmailService = employeeEmailService;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void createEmployee(EmployeeCreateRequest employeeCreateRequest) {
        this.isEmailAlreadyExist(employeeCreateRequest.getEmail());
        String password = RandomStringUtils.random(9, true, true);
        EmployeeEntity employeeEntity = new EmployeeEntity(
                UUID.randomUUID().toString(),
                employeeCreateRequest.getFirstname(),
                employeeCreateRequest.getLastname(),
                employeeCreateRequest.getEmail(),
                employeeCreateRequest.getGender(),
                employeeCreateRequest.getRole(),
                employeeCreateRequest.getBirthday(),
                this.generateUsername(),
                passwordEncoder.encode(password)
        );
        try {
            employeeRepository.save(employeeEntity);
            employeeEmailService.sendUsernameAndPasswordMail(employeeEntity, password);
        } catch (Exception e) {
            throw new GlobalException(e.getMessage());
        }


    }

    private String generateUsername() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddhhmmss");
        return LocalDateTime.now().format(formatter);
    }


    @Override
    public void updateEmployee(String id, EmployeeUpdateRequest employeeUpdateRequest) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id)
                .orElseThrow(() -> new GlobalException("Employee not found"));

        if (!employeeEntity.getEmail().equals(employeeUpdateRequest.getEmail())) {
            this.isEmailAlreadyExist(employeeUpdateRequest.getEmail());
        }

        employeeEntity.setFirstname(employeeUpdateRequest.getFirstname());
        employeeEntity.setLastname(employeeUpdateRequest.getLastname());
        employeeEntity.setEmail(employeeUpdateRequest.getEmail());
        employeeEntity.setGender(employeeUpdateRequest.getGender());
        employeeEntity.setRole(employeeUpdateRequest.getRole());

        employeeRepository.update(employeeEntity);
    }

    private void isEmailAlreadyExist(String email) {
        if (employeeRepository.findByEmail(email).isPresent()) {
            throw new GlobalException("Email is already exist");
        }
    }


    @Override
    public void updatePassword(String id, EmployeePasswordUpdateRequest employeePasswordUpdateRequest) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id)
                .orElseThrow(() -> new GlobalException("Employee  is not found "));
        if (!passwordEncoder.matches(employeePasswordUpdateRequest.getOldPassword(), employeeEntity.getPassword())) {
            throw new GlobalException("Password is Wrong ");
        }
        employeeEntity.setPassword(passwordEncoder.encode(employeePasswordUpdateRequest.getNewPassword()));
        employeeRepository.update(employeeEntity);
    }


    @Override
    public List<EmployeesResponse> getAllEmployees() {
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
        List<EmployeesResponse> employeesResponses = new ArrayList<>();
        for (EmployeeEntity employee : employeeEntities) {
            EmployeesResponse employeesResponse = new EmployeesResponse(
                    employee.getId(),
                    employee.getFirstname(),
                    employee.getLastname(),
                    employee.getEmail(),
                    employee.getGender(),
                    employee.getBirthday(),
                    employee.getRole(),
                    employee.getUsername()
            );
            employeesResponses.add(employeesResponse);
        }
        return employeesResponses;
    }


}
