package api.humanresource.model.request;

import jakarta.validation.constraints.NotNull;

public class PaginationRequest {
    @NotNull
    private Integer pageNumber;
    @NotNull
    private Integer pageSize;

    public PaginationRequest(Integer pageNumber, Integer pageSize) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }


}
