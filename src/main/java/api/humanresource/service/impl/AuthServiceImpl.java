package api.humanresource.service.impl;

import api.humanresource.model.entity.EmployeeEntity;
import api.humanresource.model.request.EmployeeLoginRequest;
import api.humanresource.repository.EmployeeRepository;
import api.humanresource.service.AuthService;
import api.humanresource.util.exception.GlobalException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
class AuthServiceImpl implements AuthService {

    private final EmployeeRepository employeeRepository;

    public AuthServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    @Override
    public void login(EmployeeLoginRequest employeeLoginRequest) {
        Optional<EmployeeEntity> employeeEntity = employeeRepository.findByUsername(employeeLoginRequest.getUsername());
        if (employeeEntity.isEmpty() || !employeeEntity.get().getPassword().equals(employeeLoginRequest.getPassword())) {
            throw new GlobalException("password or username is wrong");
        }
    }
}
