package api.humanresource.service;

import api.humanresource.model.enums.LeaveStatus;
import api.humanresource.model.request.Leave.LeaveCreateRequest;
import api.humanresource.model.request.Leave.LeavePaginationAndFilterRequest;
import api.humanresource.model.request.Leave.LeaveStatusUpdateRequest;
import api.humanresource.model.response.EmployeesResponse;
import api.humanresource.model.response.LeaveAllResponse;
import api.humanresource.model.response.LeaveEmployeeResponse;

import java.util.List;

public interface LeaveService {

    void create(LeaveCreateRequest leaveCreateRequest);

    void updateStatus(LeaveStatusUpdateRequest leaveStatusUpdateRequest);

    List<LeaveEmployeeResponse> findLeavesByEmployeeId(String employeeId,LeavePaginationAndFilterRequest leavePaginationAndFilterRequest);

    List<LeaveAllResponse> getLeavesByStatus(LeaveStatus leaveStatus);

    List<EmployeesResponse> getEmployeesOnLeave();
}
