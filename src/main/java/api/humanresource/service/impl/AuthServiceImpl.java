package api.humanresource.service.impl;

import api.humanresource.model.entity.EmployeeEntity;
import api.humanresource.model.request.EmployeeLoginRequest;
import api.humanresource.repository.EmployeeRepository;
import api.humanresource.service.AuthService;
import api.humanresource.util.exception.GlobalException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
class AuthServiceImpl implements AuthService {

    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(EmployeeRepository employeeRepository, PasswordEncoder passwordEncoder) {
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void login(EmployeeLoginRequest employeeLoginRequest) {
        EmployeeEntity employeeEntity = employeeRepository.findByUsername(employeeLoginRequest.getUsername())
                .orElseThrow(() -> new GlobalException("password or username is wrong"));

        if (!passwordEncoder.matches(employeeLoginRequest.getPassword(), employeeEntity.getPassword())) {
            throw new GlobalException("password or username is wrong");

        }
    }
}
