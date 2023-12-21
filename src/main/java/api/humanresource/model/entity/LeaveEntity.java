package api.humanresource.model.entity;

import api.humanresource.model.enums.LeaveType;
import api.humanresource.model.enums.Status;

import java.time.LocalDate;

@SuppressWarnings("unused")
public class LeaveEntity {

    private Integer id;
    private LocalDate startDate;
    private LocalDate finishDate;
    private LeaveType type;
    private String explanation;

    private Status status;
    private String employeeId;


    public LeaveEntity(LocalDate startDate, LocalDate finishDate, LeaveType type, String explanation, Status status, String employeeId) {

        this.startDate = startDate;
        this.finishDate = finishDate;
        this.type = type;
        this.explanation = explanation;
        this.status = status;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
