package api.humanresource.service;

import api.humanresource.model.entity.LeaveEntity;

public interface LeaveEmailService {
    void sendLeaveStatusChange(LeaveEntity leaveEntity);
}
