package api.humanResource.service.Impl;

import api.humanResource.model.entity.EmployeeEntity;
import api.humanResource.model.request.EmployeeLoginRequest;
import api.humanResource.repository.EmployeeRepository;
import api.humanResource.service.AuthService;
import api.humanResource.util.exception.EmployeeException;

public class AuthServiceImpl implements AuthService {
    private final EmployeeRepository employeeRepository;

    public AuthServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void login(EmployeeLoginRequest employeeLoginRequest) {
        EmployeeEntity employeeEntity = employeeRepository.findByUsername(employeeLoginRequest.getUsername());

        if (!employeeLoginRequest.getPassword().equals(employeeEntity.getPassword())) {
            throw new EmployeeException("password or username wrong");
        }
    }
}
