package api.humanresource.model;

import api.humanresource.model.entity.EmployeeEntity;
import api.humanresource.model.response.LeaveAllResponse;

public class EmployeeEntityToEmployee {
    public static LeaveAllResponse.Employee toEmployee(EmployeeEntity employeeEntity) {
        LeaveAllResponse.Employee employee = new LeaveAllResponse.Employee();
        employee.setEmployeeId(employeeEntity.getId());
        employee.setFirstName(employeeEntity.getFirstname());
        employee.setLastName(employeeEntity.getLastname());
        return employee;
    }
}
