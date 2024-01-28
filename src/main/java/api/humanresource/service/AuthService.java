package api.humanresource.service;

import api.humanresource.model.request.employee.EmployeeLoginRequest;

public interface AuthService {

    void login(EmployeeLoginRequest employeeLoginRequest);
}
