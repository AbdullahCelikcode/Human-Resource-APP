package api.humanresource.model.request;

import api.humanresource.model.enums.LeaveType;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class LeaveCreateRequest {

    @NotNull
    @Future(message = "start date can not be past date ")
    private LocalDate startDate;
    @NotNull
    @Future(message = "finish date can not be past date ")
    private LocalDate finishDate;
    @NotNull
    private LeaveType type;
    private String explanation;
    @NotNull
    private String employeeId;

    @AssertTrue(message = "finish date  cannot before start date")
    private boolean isValid() {
        return this.startDate.isBefore(this.finishDate);
    }


    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getFinishDate() {
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
