package api.humanresource.repository;

import api.humanresource.model.entity.EmployeeEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {

    void save(EmployeeEntity employeeEntity);

    void update(EmployeeEntity employeeEntity);

    List<EmployeeEntity> findAll();

    Optional<EmployeeEntity> findById(String employeeId);

    Optional<EmployeeEntity> findByUsername(String username);

    Optional<EmployeeEntity> findByEmail(String email);


}
