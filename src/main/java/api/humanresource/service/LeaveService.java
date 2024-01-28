package api.humanresource.service;

import api.humanresource.model.enums.LeaveStatus;
import api.humanresource.model.request.leave.LeaveCreateRequest;
import api.humanresource.model.request.leave.LeavesListRequest;
import api.humanresource.model.request.leave.LeaveStatusUpdateRequest;
import api.humanresource.model.request.PaginationRequest;
import api.humanresource.model.response.EmployeesResponse;
import api.humanresource.model.response.LeaveAllResponse;
import api.humanresource.model.response.LeaveEmployeeResponse;

import java.util.List;

public interface LeaveService {

    void create(LeaveCreateRequest leaveCreateRequest);

    void updateStatus(LeaveStatusUpdateRequest leaveStatusUpdateRequest);

    List<LeaveEmployeeResponse> findLeavesByEmployeeId(String employeeId, LeavesListRequest leavesListRequest);

    List<LeaveAllResponse> getLeavesByStatus(LeaveStatus leaveStatus, PaginationRequest paginationRequest);

    List<EmployeesResponse> getEmployeesOnLeave();
}
