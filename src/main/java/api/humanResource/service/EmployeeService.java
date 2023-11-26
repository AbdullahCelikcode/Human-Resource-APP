package api.humanResource.service;

import api.humanResource.model.request.EmployeeCreateRequest;
import api.humanResource.model.request.EmployeeLoginRequest;
import api.humanResource.model.request.EmployeePasswordUpdateRequest;
import api.humanResource.model.request.EmployeeUpdateRequest;
import api.humanResource.model.response.EmployeesResponse;

import java.util.List;

public interface EmployeeService {
    void createEmployee(EmployeeCreateRequest employeeCreateRequest);

    void updateEmployee(EmployeeUpdateRequest employeeUpdateRequest,String employeeId);

    void updatePassword(EmployeePasswordUpdateRequest employeePasswordUpdateRequest, String employeeId) ;



    List<EmployeesResponse> getAllEmployees();
}
