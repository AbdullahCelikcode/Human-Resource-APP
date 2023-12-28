package api.humanresource.model.request;

import api.humanresource.model.enums.LeaveType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.UUID;

import java.time.LocalDate;

public class LeaveCreateRequest {

    @NotNull
    @FutureOrPresent(message = "start date can not be past date ")
    private LocalDate startDate;
    @NotNull
    @FutureOrPresent(message = "finish date can not be past date ")
    private LocalDate finishDate;
    @NotNull
    private LeaveType type;
    @Size(max = 250)
    private String explanation;
    @NotBlank
    @UUID
    private String employeeId;

    @JsonIgnore
    @AssertTrue(message = "finish date  cannot before start date")
    @SuppressWarnings("unused")
    private boolean isValid() {
        if (startDate == null || finishDate == null) {
            return true;
        }
        return this.startDate.isBefore(this.finishDate) || this.startDate.equals(this.finishDate);
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
