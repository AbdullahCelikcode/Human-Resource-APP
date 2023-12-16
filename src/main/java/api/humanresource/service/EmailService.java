package api.humanresource.service;

import api.humanresource.model.entity.EmployeeEntity;

public interface EmailService {

    void sendUsernameAndPasswordMail(EmployeeEntity employeeEntity);

    void sendBirthdayEmail();
}
