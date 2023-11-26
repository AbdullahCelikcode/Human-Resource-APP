package api.humanResource.repository;

import api.humanResource.model.entity.EmployeeEntity;

import java.util.List;

public interface EmployeeRepository {
    void createEmployee(EmployeeEntity employeeEntity);

    void updateEmployee(EmployeeEntity employeeEntity);


    List<String> findAllUsernames();

    List<EmployeeEntity> findAll();

    public EmployeeEntity findById(String employeeId);

    List<String> findAllEmails();

    EmployeeEntity findByUsername(String username);
}
