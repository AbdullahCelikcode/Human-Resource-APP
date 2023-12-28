package api.humanresource.model.request;

import jakarta.validation.Valid;

public class LeavePaginationAndFilterRequest {

    @Valid
    private PaginationRequest paginationRequest;
    private FilterRequest filterRequest;

    public LeavePaginationAndFilterRequest(FilterRequest filterRequest, PaginationRequest paginationRequest) {
        this.filterRequest = filterRequest;
        this.paginationRequest = paginationRequest;
    }

    public FilterRequest getFilterRequest() {
        return filterRequest;
    }

    public PaginationRequest getPaginationRequest() {
        return paginationRequest;
    }
}
