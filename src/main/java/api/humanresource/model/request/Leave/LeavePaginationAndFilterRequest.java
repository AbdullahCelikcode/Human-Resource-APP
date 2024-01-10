package api.humanresource.model.request.Leave;

import api.humanresource.model.enums.LeaveStatus;
import api.humanresource.model.request.PaginationRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class LeavePaginationAndFilterRequest {

    @Valid
    @NotNull
    private PaginationRequest paginationRequest;


    private LeaveFilter leaveFilter;


    public static class LeaveFilter {
        private LeaveStatus leaveStatus;

        public LeaveStatus getLeaveStatus() {
            return leaveStatus;
        }


    }

    public LeavePaginationAndFilterRequest(LeaveFilter filter, PaginationRequest pagination) {
        this.leaveFilter = filter;
        this.paginationRequest = pagination;
    }

    public LeaveFilter getFilter() {
        return leaveFilter;
    }

    public PaginationRequest getPaginationRequest() {
        return paginationRequest;
    }
}

