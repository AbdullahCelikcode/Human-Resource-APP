package api.humanresource.repository.impl;

import api.humanresource.model.entity.LeaveEntity;
import api.humanresource.model.mappers.LeaveMapper;
import api.humanresource.repository.LeaveRepository;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class LeaveRepositoryImpl implements LeaveRepository {
    private final Sql2o sql2o;
    private static final String INSERT_QUERY = "INSERT INTO LEAVE_TABLE (START_DATE,FINISH_DATE,TYPE,EXPLANATION,EMPLOYEE_ID) " +
            "VALUES (:startDate,:finishDate,:type,:explanation,:employeeId)";
    private static final String GET_LEAVES_QUERY = "SELECT START_DATE, FINISH_DATE, TYPE" +
            " FROM LEAVE_TABLE WHERE EMPLOYEE_ID=:employeeId ";

    public LeaveRepositoryImpl(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    public void save(LeaveEntity leaveEntity) {


        try (Connection con = sql2o.open(); Query query = con.createQuery(INSERT_QUERY)) {
            query
                    .addParameter(LeaveMapper.START_DATE.getField(), leaveEntity.getStartDate())
                    .addParameter(LeaveMapper.FINISH_DATE.getField(), leaveEntity.getFinishDate())
                    .addParameter(LeaveMapper.TYPE.getField(), leaveEntity.getType())
                    .addParameter(LeaveMapper.EXPLANATION.getField(), leaveEntity.getExplanation())
                    .addParameter(LeaveMapper.EMPOYEEID.getField(), leaveEntity.getEmployeeId())
                    .executeUpdate();

        }
    }

    @Override
    public List<LeaveEntity> getLeaves(String id) {
        try (Connection con = sql2o.open(); Query query = con.createQuery(GET_LEAVES_QUERY)) {
            return query
                    .addParameter(LeaveMapper.EMPOYEEID.getField(), id)
                    .setColumnMappings(LeaveMapper.getMapping())
                    .executeAndFetch(LeaveEntity.class);
        }
    }

}

