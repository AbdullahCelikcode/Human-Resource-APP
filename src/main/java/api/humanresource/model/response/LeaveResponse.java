package api.humanresource.model.response;

import api.humanresource.model.enums.LeaveType;

import java.util.Date;

public class LeaveResponse {
    private Date startDate;
    private Date finishDate;
    private LeaveType type;

    public LeaveResponse(Date startDate, Date finishDate, LeaveType type) {
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.type = type;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public LeaveType getType() {
        return type;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public void setType(LeaveType type) {
        this.type = type;
    }
}
