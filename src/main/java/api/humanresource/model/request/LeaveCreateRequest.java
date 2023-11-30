package api.humanresource.model.request;

import api.humanresource.model.enums.LeaveType;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.sql.Date;

public class LeaveCreateRequest {
    @NotNull
    @Future(message = "start date can not be past date ")
    private Date startDate;
    @NotNull
    @Future(message = "finish date can not be past date ")
    private Date finishDate;
    private LeaveType type;
    private String explanation;
    @NotNull
    private String employeeId;

    public Date getStartDate() {
        return startDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public LeaveType getType() {
        return type;
    }

    public String getExplanation() {
        return explanation;
    }

    public String getEmployeeId() {
        return employeeId;
    }
}
