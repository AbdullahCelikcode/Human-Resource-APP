package api.humanresource.service;

import api.humanresource.model.request.Employee.EmployeeLoginRequest;

public interface AuthService {

    void login(EmployeeLoginRequest employeeLoginRequest);
}
