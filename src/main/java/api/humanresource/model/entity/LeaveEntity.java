package api.humanresource.model.entity;

import api.humanresource.model.enums.LeaveStatus;
import api.humanresource.model.enums.LeaveType;

import java.time.LocalDate;


public class LeaveEntity {

    private Long id;
    private LocalDate startDate;
    private LocalDate finishDate;
    private LeaveType type;
    private String explanation;

    private LeaveStatus leaveStatus;
    private String employeeId;


    public LeaveEntity(LocalDate startDate,
                       LocalDate finishDate,
                       LeaveType type,
                       String explanation,
                       LeaveStatus leaveStatus,
                       String employeeId) {

        this.startDate = startDate;
        this.finishDate = finishDate;
        this.type = type;
        this.explanation = explanation;
        this.leaveStatus = leaveStatus;
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

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(LocalDate finishDate) {
        this.finishDate = finishDate;
    }

    public LeaveType getType() {
        return type;
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

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public LeaveStatus getStatus() {
        return leaveStatus;
    }

    public void setStatus(LeaveStatus leaveStatus) {
        this.leaveStatus = leaveStatus;
    }
}
