package api.humanresource.service;

import api.humanresource.model.entity.EmployeeEntity;

public interface EmployeeEmailService {

    void sendUsernameAndPasswordMail(EmployeeEntity employeeEntity);

    void sendBirthdayEmail();
}
