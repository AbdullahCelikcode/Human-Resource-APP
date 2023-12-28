package api.humanresource.service;

import api.humanresource.model.enums.LeaveStatus;
import api.humanresource.model.request.LeaveCreateRequest;
import api.humanresource.model.request.LeaveStatusUpdateRequest;
import api.humanresource.model.response.EmployeesResponse;
import api.humanresource.model.response.LeaveAllResponse;
import api.humanresource.model.response.LeaveEmployeeResponse;

import java.util.List;

public interface LeaveService {

    void create(LeaveCreateRequest leaveCreateRequest);

    void updateStatus(LeaveStatusUpdateRequest leaveStatusUpdateRequest);

    List<LeaveEmployeeResponse> findLeavesByEmployeeId(String employeeId);

    List<LeaveAllResponse> getLeavesByStatus(LeaveStatus leaveStatus);

    List<EmployeesResponse> getEmployeesOnLeave();
}
