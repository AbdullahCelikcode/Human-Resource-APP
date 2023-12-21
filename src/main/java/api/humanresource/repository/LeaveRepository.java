package api.humanresource.repository;

import api.humanresource.model.entity.LeaveEntity;

import java.time.LocalDate;
import java.util.List;

public interface LeaveRepository {

    void save(LeaveEntity leaveEntity);

    List<LeaveEntity> findLeavesById(String employeeId);

    boolean isExistByDate(LocalDate startDate, LocalDate finishDate, String employeeId);

}
