package api.humanresource.model.request.Leave;

import api.humanresource.model.enums.LeaveStatus;

public class LeaveFilterByStatusRequest {

    private LeaveStatus status;

    public LeaveStatus getStatus() {
        return status;
    }


}
