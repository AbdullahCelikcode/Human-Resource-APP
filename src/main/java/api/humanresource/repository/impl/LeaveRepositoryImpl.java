package api.humanresource.repository.impl;

import api.humanresource.model.entity.LeaveEntity;
import api.humanresource.model.enums.LeaveStatus;
import api.humanresource.repository.LeaveRepository;
import api.humanresource.repository.mapping.LeaveMapper;
import api.humanresource.util.exception.GlobalException;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
class LeaveRepositoryImpl implements LeaveRepository {

    private final Sql2o sql2o;

    public LeaveRepositoryImpl(Sql2o sql2o) {
        this.sql2o = sql2o;
    }


    private static final String INSERT_QUERY =
            "INSERT INTO `LEAVE` (START_DATE,FINISH_DATE,TYPE,EXPLANATION,STATUS,EMPLOYEE_ID) " +
                    "VALUES (:startDate,:finishDate,:type,:explanation,:status,:employeeId)";
    private static final String UPDATE_QUERY =
            "UPDATE  `LEAVE` SET START_DATE=:startDate,FINISH_DATE=:finishDate,TYPE=:type," +
                    "EXPLANATION=:explanation,STATUS=:status,EMPLOYEE_ID=:employeeId WHERE ID=:id";
    private static final String GET_LEAVES_QUERY =
            "SELECT ID, START_DATE, FINISH_DATE, EXPLANATION, STATUS, TYPE " +
                    "FROM `LEAVE`" +
                    " WHERE EMPLOYEE_ID=:employeeId " +
                    "ORDER BY ID" +
                    " LIMIT :limit OFFSET :offset ";

    private static final String IS_EXIST_BY_DATE_QUERY = "SELECT " +
            "    IF(EXISTS (SELECT START_DATE,FINISH_DATE,EMPLOYEE_ID FROM `LEAVE` " +
            "        WHERE EMPLOYEE_ID=:employeeId AND (START_DATE=:startDate" +
            "        OR FINISH_DATE=:finishDate))" +
            ", 'TRUE', 'FALSE') ";

    private static final String GET_LEAVES_ON_PENDING = "SELECT ID, START_DATE, FINISH_DATE,EXPLANATION,STATUS, TYPE,EMPLOYEE_ID" +
            "             FROM `LEAVE` WHERE STATUS=:status ";

    private static final String FIND_BY_ID_QUERY = "SELECT ID,START_DATE, FINISH_DATE,EXPLANATION,STATUS,TYPE,EMPLOYEE_ID " +
            " FROM `LEAVE` WHERE ID=:id";


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
        } catch (RuntimeException e) {
            throw new GlobalException(e.getMessage());
        }
    }

    @Override
    public void update(LeaveEntity leaveEntity) {
        try (Connection con = sql2o.open(); Query query = con.createQuery(UPDATE_QUERY)) {
            query
                    .addParameter(LeaveMapper.START_DATE.getField(), leaveEntity.getStartDate())
                    .addParameter(LeaveMapper.FINISH_DATE.getField(), leaveEntity.getFinishDate())
                    .addParameter(LeaveMapper.TYPE.getField(), leaveEntity.getType())
                    .addParameter(LeaveMapper.EXPLANATION.getField(), leaveEntity.getExplanation())
                    .addParameter(LeaveMapper.STATUS.getField(), leaveEntity.getStatus())
                    .addParameter(LeaveMapper.EMPLOYEE_ID.getField(), leaveEntity.getEmployeeId())
                    .addParameter(LeaveMapper.ID.getField(), leaveEntity.getId())
                    .executeUpdate();
        }
    }


    @Override
    public List<LeaveEntity> findLeavesByEmployeeId(String employeeId, Integer pageNumber, Integer pageSize) {

        try (Connection con = sql2o.open(); Query query = con.createQuery(GET_LEAVES_QUERY)) {
            return query
                    .addParameter(LeaveMapper.EMPLOYEE_ID.getField(), employeeId)
                    .addParameter("offset", pageNumber)
                    .addParameter("limit", pageSize)
                    .setColumnMappings(LeaveMapper.getMapping())
                    .executeAndFetch(LeaveEntity.class);
        }
    }

    @Override
    public Optional<LeaveEntity> findLeavesById(Long id) {
        try (Connection con = sql2o.open(); Query query = con.createQuery(FIND_BY_ID_QUERY)) {
            return Optional.ofNullable(query
                    .addParameter(LeaveMapper.ID.getField(), id)
                    .setColumnMappings(LeaveMapper.getMapping())
                    .executeAndFetchFirst(LeaveEntity.class));
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

    @Override
    public List<LeaveEntity> findLeavesByStatus(LeaveStatus status) {
        try (Connection con = sql2o.open(); Query query = con.createQuery(GET_LEAVES_ON_PENDING)) {
            return query
                    .addParameter(LeaveMapper.STATUS.getField(), status)
                    .setColumnMappings(LeaveMapper.getMapping())
                    .executeAndFetch(LeaveEntity.class);
        }
    }
}

