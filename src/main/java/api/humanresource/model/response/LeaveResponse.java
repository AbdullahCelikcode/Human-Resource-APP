package api.humanresource.model.response;

import api.humanresource.model.enums.LeaveType;

import java.time.LocalDate;
@SuppressWarnings("unused")
public class LeaveResponse {

    private LocalDate startDate;
    private LocalDate finishDate;

    private String explanation;
    private LeaveType type;

    public LeaveResponse(LocalDate startDate, LocalDate finishDate, String explanation, LeaveType type) {
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.explanation = explanation;
        this.type = type;
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
}
