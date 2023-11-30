package api.humanresource.repository;

import api.humanresource.model.entity.LeaveEntity;

import java.util.List;

public interface LeaveRepository {

    void save(LeaveEntity leaveEntity);

    List<LeaveEntity> getLeaves(String id);
}
