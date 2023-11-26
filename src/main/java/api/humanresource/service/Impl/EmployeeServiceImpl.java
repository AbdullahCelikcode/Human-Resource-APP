package api.humanresource.service.Impl;

import api.humanresource.model.entity.EmployeeEntity;
import api.humanresource.model.request.EmployeeCreateRequest;
import api.humanresource.model.request.EmployeePasswordUpdateRequest;
import api.humanresource.model.request.EmployeeUpdateRequest;
import api.humanresource.model.response.EmployeesResponse;
import api.humanresource.repository.EmployeeRepository;
import api.humanresource.service.EmployeeService;
import api.humanresource.util.exception.EmployeeException;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void createEmployee(EmployeeCreateRequest employeeCreateRequest) {
        isEmailAlreadyExist(employeeCreateRequest.getEmail());

        EmployeeEntity employeeEntity = new EmployeeEntity(
                UUID.randomUUID().toString(),
                employeeCreateRequest.getFirstname(),
                employeeCreateRequest.getLastname(),
                employeeCreateRequest.getEmail(),
                employeeCreateRequest.getGender(),
                employeeCreateRequest.getRole(),
                this.generateUsername(),
                RandomStringUtils.random(9, true, true)

        );
        employeeRepository.save(employeeEntity);
    }

    private String generateUsername() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddhhmmss");

        return LocalDateTime.now().format(formatter);


    }

    @Override
    public void updateEmployee(String id, EmployeeUpdateRequest employeeUpdateRequest) {

        EmployeeEntity employeeEntity = employeeRepository.findById(id);

        if (!employeeEntity.getEmail().equals(employeeUpdateRequest.getEmail())) {
            isEmailAlreadyExist(employeeUpdateRequest.getEmail());
        }

        employeeEntity.setFirstname(employeeUpdateRequest.getFirstname());
        employeeEntity.setLastname(employeeUpdateRequest.getLastname());
        employeeEntity.setEmail(employeeUpdateRequest.getEmail());
        employeeEntity.setGender(employeeUpdateRequest.getGender());
        employeeEntity.setRole(employeeUpdateRequest.getRole());

        employeeRepository.update(employeeEntity);


    }

    public void isEmailAlreadyExist(String email) {

        if (employeeRepository.findByEmail(email) != null) {
            throw new EmployeeException("Email is already exist");
        }
    }

    @Override
    public void updatePassword(EmployeePasswordUpdateRequest employeePasswordUpdateRequest) {


        EmployeeEntity employeeEntity = employeeRepository.findByUsername(employeePasswordUpdateRequest.getUsername());

        if (!employeeEntity.getPassword().equals(employeePasswordUpdateRequest.getOldPassword())) {

            throw new EmployeeException("Password Wrong");
        }

        employeeEntity.setPassword(employeePasswordUpdateRequest.getNewPassword());
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
                    employee.getRole(),
                    employee.getUsername()
            );

            employeesResponses.add(employeesResponse);
        }

        return employeesResponses;
    }


}
