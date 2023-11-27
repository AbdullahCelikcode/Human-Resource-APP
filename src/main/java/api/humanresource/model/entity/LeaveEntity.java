package api.humanresource.model.entity;

import api.humanresource.model.enums.LeaveType;

import java.util.Date;

public class LeaveEntity {
    private Integer id;
    private Date startDate;
    private Date finishDate;
    private LeaveType type;

    private String explanation;

    private String employeeID;

    public LeaveEntity(Integer id, Date startDate, Date finishDate, LeaveType type, String explanation, String employeeID) {
        this.id = id;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.type = type;
        this.explanation = explanation;
        this.employeeID = employeeID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
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

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }
}
