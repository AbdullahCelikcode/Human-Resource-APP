package api.humanresource.repository.impl;

import api.humanresource.model.entity.LeaveEntity;
import api.humanresource.repository.LeaveRepository;
import api.humanresource.repository.mapping.LeaveMapper;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import java.time.LocalDate;
import java.util.List;

@Repository
public class LeaveRepositoryImpl implements LeaveRepository {

    private final Sql2o sql2o;

    public LeaveRepositoryImpl(Sql2o sql2o) {
        this.sql2o = sql2o;
    }


    private static final String INSERT_QUERY = "INSERT INTO LEAVE_TABLE (START_DATE,FINISH_DATE,TYPE,EXPLANATION,STATUS,EMPLOYEE_ID) " +
            "VALUES (:startDate,:finishDate,:type,:explanation,:status,:employeeId)";
    private static final String GET_LEAVES_QUERY = "SELECT ID, START_DATE, FINISH_DATE,EXPLANATION,STATUS, TYPE" +
            " FROM LEAVE_TABLE WHERE EMPLOYEE_ID=:employeeId ";

    private static final String IS_EXIST_BY_DATE_QUERY = "SELECT " +
            "    IF(EXISTS (SELECT START_DATE,FINISH_DATE,EMPLOYEE_ID FROM leave_table " +
            "        WHERE EMPLOYEE_ID=:employeeId AND (START_DATE=:startDate" +
            "        OR FINISH_DATE=:finishDate))" +
            ", 'TRUE', 'FALSE') ";


    public void save(LeaveEntity leaveEntity) {
        try (Connection con = sql2o.open(); Query query = con.createQuery(INSERT_QUERY)) {
            query
                    .addParameter(LeaveMapper.START_DATE.getField(), leaveEntity.getStartDate())
                    .addParameter(LeaveMapper.FINISH_DATE.getField(), leaveEntity.getFinishDate())
                    .addParameter(LeaveMapper.TYPE.getField(), leaveEntity.getType())
                    .addParameter(LeaveMapper.EXPLANATION.getField(), leaveEntity.getExplanation())
                    .addParameter(LeaveMapper.STATUS.getField(), leaveEntity.getStatus())
                    .addParameter(LeaveMapper.EMPLOYEE_ID.getField(), leaveEntity.getEmployeeId())
                    .executeUpdate();
        }
    }


    @Override
    public List<LeaveEntity> findLeavesById(String employeeId) {
        try (Connection con = sql2o.open(); Query query = con.createQuery(GET_LEAVES_QUERY)) {
            return query
                    .addParameter(LeaveMapper.EMPLOYEE_ID.getField(), employeeId)
                    .setColumnMappings(LeaveMapper.getMapping())
                    .executeAndFetch(LeaveEntity.class);
        }
    }

    @Override
    public boolean isExistByDate(LocalDate startDate, LocalDate finishDate, String employeeId) {
        try (Connection con = sql2o.open(); Query query = con.createQuery(IS_EXIST_BY_DATE_QUERY)) {
            return query
                    .addParameter(LeaveMapper.START_DATE.getField(), startDate)
                    .addParameter(LeaveMapper.FINISH_DATE.getField(), finishDate)
                    .addParameter(LeaveMapper.EMPLOYEE_ID.getField(), employeeId)
                    .executeAndFetchFirst(Boolean.class);
        }
    }


}

