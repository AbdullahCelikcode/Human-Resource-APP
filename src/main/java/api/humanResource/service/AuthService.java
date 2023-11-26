package api.humanResource.service;

import api.humanResource.model.request.EmployeeLoginRequest;

public interface AuthService {
    void login(EmployeeLoginRequest employeeLoginRequest);
}
