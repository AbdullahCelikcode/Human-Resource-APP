package api.humanresource.service;

import api.humanresource.model.request.LeaveCreateRequest;
import api.humanresource.model.response.LeaveResponse;

import java.util.List;

public interface LeaveService {
    void add(LeaveCreateRequest leaveCreateRequest);

    List<LeaveResponse> getLeaves(String id);
}
