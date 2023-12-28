package api.humanresource.service;

import api.humanresource.model.entity.EmployeeEntity;
import api.humanresource.model.entity.LeaveEntity;

public interface EmployeeEmailService {

    void sendUsernameAndPasswordMail(EmployeeEntity employeeEntity,String password);

    void sendBirthdayEmail();

    void sendLeaveStatusChange(LeaveEntity leaveEntity);
}
