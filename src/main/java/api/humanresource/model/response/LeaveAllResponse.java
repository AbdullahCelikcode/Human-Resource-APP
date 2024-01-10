package api.humanresource.model.response;

import api.humanresource.model.enums.LeaveStatus;
import api.humanresource.model.enums.LeaveType;

import java.time.LocalDate;

public class LeaveAllResponse {
    private Long id;
    private LocalDate startDate;
    private LocalDate finishDate;

    private String explanation;

    private LeaveStatus leaveStatus;
    private LeaveType type;
    private String employeeId;



    public LeaveAllResponse(Long id, LocalDate startDate, LocalDate finishDate, String explanation, LeaveStatus leaveStatus, LeaveType type, String employeeId) {
        this.id = id;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.explanation = explanation;
        this.leaveStatus = leaveStatus;
        this.type = type;
        this.employeeId = employeeId;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public void setStatus(LeaveStatus leaveStatus) {
        this.leaveStatus = leaveStatus;
    }

    public LeaveStatus getStatus() {
        return leaveStatus;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }
}
