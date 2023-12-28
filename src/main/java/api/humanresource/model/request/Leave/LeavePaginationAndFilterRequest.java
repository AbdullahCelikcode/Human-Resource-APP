package api.humanresource.model.request.Leave;

import api.humanresource.model.request.PaginationRequest;
import jakarta.validation.Valid;

public class LeavePaginationAndFilterRequest {

    @Valid
    private PaginationRequest paginationRequest;
    private LeaveFilterByStatusRequest leaveFilterByStatusRequest;

    public LeavePaginationAndFilterRequest(LeaveFilterByStatusRequest leaveFilterByStatusRequest, PaginationRequest paginationRequest) {
        this.leaveFilterByStatusRequest = leaveFilterByStatusRequest;
        this.paginationRequest = paginationRequest;
    }

    public LeaveFilterByStatusRequest getFilterRequest() {
        return leaveFilterByStatusRequest;
    }

    public PaginationRequest getPaginationRequest() {
        return paginationRequest;
    }
}
