package api.humanresource.service;

import api.humanresource.model.request.employee.EmployeeCreateRequest;
import api.humanresource.model.request.employee.EmployeePasswordUpdateRequest;
import api.humanresource.model.request.employee.EmployeeUpdateRequest;
import api.humanresource.model.response.EmployeesResponse;

import java.util.List;

public interface EmployeeService {

    void createEmployee(EmployeeCreateRequest employeeCreateRequest);

    void updateEmployee(String id, EmployeeUpdateRequest employeeUpdateRequest);

    void updatePassword(String id, EmployeePasswordUpdateRequest employeePasswordUpdateRequest);

    List<EmployeesResponse> getAllEmployees();


}
