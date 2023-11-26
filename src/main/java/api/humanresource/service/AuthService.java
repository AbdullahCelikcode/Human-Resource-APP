package api.humanresource.service;

import api.humanresource.model.request.EmployeeLoginRequest;

public interface AuthService {
    void login(EmployeeLoginRequest employeeLoginRequest);
}
