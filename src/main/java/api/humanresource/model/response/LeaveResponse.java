package api.humanresource.model.response;

import api.humanresource.model.enums.LeaveType;
import api.humanresource.model.enums.Status;

import java.time.LocalDate;

@SuppressWarnings("unused")
public class LeaveResponse {
    private Integer id;
    private LocalDate startDate;
    private LocalDate finishDate;

    private String explanation;

    private Status status;
    private LeaveType type;
    private String employeeId;


    public LeaveResponse(Integer id, LocalDate startDate, LocalDate finishDate, String explanation, Status status, LeaveType type, String employeeId) {
        this.id = id;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.explanation = explanation;
        this.status = status;
        this.type = type;
        this.employeeId = employeeId;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setFinishDate(LocalDate finishDate) {
        this.finishDate = finishDate;
    }

    public void setType(LeaveType type) {
        this.type = type;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }
}
