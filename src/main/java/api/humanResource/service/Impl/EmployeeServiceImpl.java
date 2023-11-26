package api.humanResource.service.Impl;

import api.humanResource.util.exception.EmployeeException;
import api.humanResource.model.entity.EmployeeEntity;
import api.humanResource.model.request.EmployeeCreateRequest;
import api.humanResource.model.request.EmployeeLoginRequest;
import api.humanResource.model.request.EmployeePasswordUpdateRequest;
import api.humanResource.model.request.EmployeeUpdateRequest;
import api.humanResource.model.response.EmployeesResponse;
import api.humanResource.repository.EmployeeRepository;
import api.humanResource.service.EmployeeService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
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
                this.generateUsername(employeeCreateRequest.getFirstname()),
                RandomStringUtils.random(9, true, true)

        );
        employeeRepository.createEmployee(employeeEntity);
    }

    private String generateUsername(String firstname) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hhmmss");

        String username = firstname + formatter.format(LocalDate.now());

        if (employeeRepository.findAllUsernames().contains(username)) {
            return (username + timeFormatter.format(LocalTime.now()));
        }
        return username;
    }

    @Override
    public void updateEmployee(EmployeeUpdateRequest employeeUpdateRequest, String employeeId) {

        EmployeeEntity employeeEntity = employeeRepository.findById(employeeId);

        if (!employeeEntity.getEmail().equals(employeeUpdateRequest.getEmail())) {
            isEmailAlreadyExist(employeeUpdateRequest.getEmail());
        }

        employeeEntity.setFirstname(employeeUpdateRequest.getFirstname());
        employeeEntity.setLastname(employeeUpdateRequest.getLastname());
        employeeEntity.setEmail(employeeUpdateRequest.getEmail());
        employeeEntity.setGender(employeeUpdateRequest.getGender());
        employeeEntity.setRole(employeeUpdateRequest.getRole());
        employeeEntity.setUsername(employeeUpdateRequest.getUsername());
        employeeEntity.setPassword(employeeUpdateRequest.getPassword());

        employeeRepository.updateEmployee(employeeEntity);


    }

    public void isEmailAlreadyExist(String email) {
        if (employeeRepository.findAllEmails().contains(email)) {
            throw new EmployeeException("Email is already exist");
        }
    }

    @Override
    public void updatePassword(EmployeePasswordUpdateRequest employeePasswordUpdateRequest, String employeeId) {


        EmployeeEntity employeeEntity = employeeRepository.findById(employeeId);

        if (!employeeEntity.getPassword().equals(employeePasswordUpdateRequest.getOldPassword())) {

            throw new EmployeeException("Password Wrong");
        }

        employeeEntity.setPassword(employeePasswordUpdateRequest.getNewPassword());
        employeeRepository.updateEmployee(employeeEntity);

    }

    @Override
    public void login(EmployeeLoginRequest employeeLoginRequest) {
        EmployeeEntity employeeEntity = employeeRepository.findByUsername(employeeLoginRequest.getUsername());

        if (!employeeLoginRequest.getPassword().equals(employeeEntity.getPassword())) {
            throw new EmployeeException("password or username wrong");
        }
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
