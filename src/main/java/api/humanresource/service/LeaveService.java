package api.humanresource.service;

import api.humanresource.model.request.LeaveCreateRequest;
import api.humanresource.model.response.LeaveResponse;

import java.util.List;

public interface LeaveService {

    void create(LeaveCreateRequest leaveCreateRequest);

    List<LeaveResponse> findLeavesById(String id);
}
