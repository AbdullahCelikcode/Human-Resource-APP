package api.humanresource.repository;

import api.humanresource.model.entity.EmployeeEntity;

import java.util.List;

public interface EmployeeRepository {
    void save(EmployeeEntity employeeEntity);

    void update(EmployeeEntity employeeEntity);


    List<EmployeeEntity> findAll();

    EmployeeEntity findById(String employeeId);


    EmployeeEntity findByUsername(String username);

    EmployeeEntity findByEmail(String email);
}
