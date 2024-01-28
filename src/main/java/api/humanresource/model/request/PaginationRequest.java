package api.humanresource.model.request;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;

public class PaginationRequest {
    @NotNull
    @Range(min = 1, max = Integer.MAX_VALUE)
    private Integer pageNumber;
    @NotNull
    @Range(min = 1, max = 50)
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
