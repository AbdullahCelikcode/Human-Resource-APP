package api.humanresource.model.request;

import api.humanresource.model.enums.Status;
import jakarta.validation.constraints.NotNull;

public class LeaveStatusUpdateRequest {
    @NotNull
    private Integer id;
    @NotNull
    private Status status;

    public Integer getId() {
        return id;
    }

    public Status getStatus() {
        return status;
    }
}
