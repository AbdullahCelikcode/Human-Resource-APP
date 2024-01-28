package api.humanresource.model.request.leave;

import api.humanresource.model.enums.LeaveStatus;
import api.humanresource.model.request.PaginationRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.Map;

public class LeavesListRequest {

    @Valid
    @NotNull
    private PaginationRequest pagination;


    private LeaveFilter filter;


    public static class LeaveFilter {
        private LeaveStatus status;

        public LeaveStatus getStatus() {
            return status;
        }

        public Map<String, Object> toMap() {
            return Map.of(
                    "status",
                    this.status
            );

        }


    }

    public LeavesListRequest(LeaveFilter filter, PaginationRequest pagination) {
        this.filter = filter;
        this.pagination = pagination;
    }

    public LeaveFilter getFilter() {
        return filter;
    }

    public PaginationRequest getPagination() {
        return pagination;
    }
}

