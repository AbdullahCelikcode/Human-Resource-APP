package api.humanresource.model.request.leave;

import api.humanresource.model.enums.LeaveStatus;

public class LeaveFilterByStatusRequest {

    private LeaveStatus status;

    public LeaveStatus getStatus() {
        return status;
    }


}
