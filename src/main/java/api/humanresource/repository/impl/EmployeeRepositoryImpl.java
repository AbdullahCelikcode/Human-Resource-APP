package api.humanresource.repository.impl;

import api.humanresource.model.entity.EmployeeEntity;
import api.humanresource.repository.EmployeeRepository;
import api.humanresource.repository.mapping.EmployeeMapper;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Repository
class EmployeeRepositoryImpl implements EmployeeRepository {

    private final Sql2o sql2o;

    public EmployeeRepositoryImpl(Sql2o sql2o) {
        this.sql2o = sql2o;

    }


    private static final String INSERT_QUERY =
            "INSERT INTO `EMPLOYEE` (ID,FIRST_NAME,LAST_NAME,EMAIL,GENDER,ROLE,BIRTHDAY, USERNAME,PASSWORD ) " +
                    "VALUES (:id,:firstname,:lastname,:email,:gender,:role,:birthday ,:username,:password)";
    private static final String UPDATE_QUERY =
            "UPDATE `EMPLOYEE` SET FIRST_NAME=:firstname,LAST_NAME=:lastname,EMAIL=:email,GENDER=:gender,ROLE=:role," +
                    "PASSWORD=:password WHERE ID=:id ";
    private static final String FIND_BY_ID_QUERY =
            "SELECT ID, FIRST_NAME, LAST_NAME, EMAIL, GENDER, ROLE ,USERNAME,PASSWORD FROM `EMPLOYEE` WHERE id=:id ";

    private static final String FIND_BY_USERNAME_QUERY =
            "SELECT ID, FIRST_NAME, LAST_NAME, EMAIL, GENDER, ROLE,USERNAME, PASSWORD FROM `EMPLOYEE` WHERE USERNAME=:username ";

    private static final String FIND_BY_EMAIL_QUERY = "SELECT EMAIL FROM `EMPLOYEE` WHERE EMAIL=:email ";

    private static final String FIND_ALL_QUERY =
            "SELECT ID, FIRST_NAME, LAST_NAME, EMAIL, GENDER, ROLE, BIRTHDAY,USERNAME, PASSWORD FROM `EMPLOYEE` ";

    private static final String FIND_ALL_DAILY =
            "SELECT e.ID, e.FIRST_NAME, e.LAST_NAME, e.EMAIL, e.GENDER," +
                    " e.ROLE ,e.USERNAME, e.BIRTHDAY " +
                    " FROM `Employee` e INNER JOIN `LEAVE` l " +
                    " ON e.ID = l.EMPLOYEE_ID" +
                    " WHERE :DATE BETWEEN l.START_DATE AND l.FINISH_DATE ";


    @Override
    public void save(EmployeeEntity employeeEntity) {
        try (Connection con = sql2o.open(); Query query = con.createQuery(INSERT_QUERY)) {
            query
                    .addParameter(EmployeeMapper.ID.getField(), employeeEntity.getId())
                    .addParameter(EmployeeMapper.FIRST_NAME.getField(), employeeEntity.getFirstname())
                    .addParameter(EmployeeMapper.LAST_NAME.getField(), employeeEntity.getLastname())
                    .addParameter(EmployeeMapper.EMAIL.getField(), employeeEntity.getEmail())
                    .addParameter(EmployeeMapper.GENDER.getField(), employeeEntity.getGender())
                    .addParameter(EmployeeMapper.ROLE.getField(), employeeEntity.getRole())
                    .addParameter(EmployeeMapper.BIRTHDAY.getField(), employeeEntity.getBirthday())
                    .addParameter(EmployeeMapper.USERNAME.getField(), employeeEntity.getUsername())
                    .addParameter(EmployeeMapper.PASSWORD.getField(), employeeEntity.getPassword())
                    .executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    @Override
    public void update(EmployeeEntity employeeEntity) {
        try (Connection con = sql2o.open(); Query query = con.createQuery(UPDATE_QUERY)) {
            query
                    .addParameter(EmployeeMapper.ID.getField(), employeeEntity.getId())
                    .addParameter(EmployeeMapper.FIRST_NAME.getField(), employeeEntity.getFirstname())
                    .addParameter(EmployeeMapper.LAST_NAME.getField(), employeeEntity.getLastname())
                    .addParameter(EmployeeMapper.EMAIL.getField(), employeeEntity.getEmail())
                    .addParameter(EmployeeMapper.GENDER.getField(), employeeEntity.getGender())
                    .addParameter(EmployeeMapper.ROLE.getField(), employeeEntity.getRole())
                    .addParameter(EmployeeMapper.PASSWORD.getField(), employeeEntity.getPassword())
                    .executeUpdate();
        }
    }


    @Override
    public Optional<EmployeeEntity> findById(String employeeId) {
        try (Connection con = sql2o.open(); Query query = con.createQuery(FIND_BY_ID_QUERY)) {
            return Optional.ofNullable(query
                    .addParameter(EmployeeMapper.ID.getField(), employeeId)
                    .setColumnMappings(EmployeeMapper.getMapping())
                    .executeAndFetchFirst(EmployeeEntity.class));
        }
    }


    @Override
    public Optional<EmployeeEntity> findByUsername(String username) {
        try (Connection con = sql2o.open(); Query query = con.createQuery(FIND_BY_USERNAME_QUERY)) {
            return Optional.ofNullable(query
                    .addParameter(EmployeeMapper.USERNAME.getField(), username)
                    .setColumnMappings(EmployeeMapper.getMapping())
                    .executeAndFetchFirst(EmployeeEntity.class));
        }
    }


    @Override
    public Optional<EmployeeEntity> findByEmail(String email) {
        try (Connection con = sql2o.open(); Query query = con.createQuery(FIND_BY_EMAIL_QUERY)) {
            return Optional.ofNullable(query
                    .addParameter(EmployeeMapper.EMAIL.getField(), email)
                    .setColumnMappings(EmployeeMapper.getMapping())
                    .executeAndFetchFirst(EmployeeEntity.class));
        }
    }


    @Override
    public List<EmployeeEntity> findAll() {
        try (Connection con = sql2o.open(); Query query = con.createQuery(FIND_ALL_QUERY)) {
            return query
                    .setColumnMappings(EmployeeMapper.getMapping())
                    .executeAndFetch(EmployeeEntity.class);
        }
    }


    @Override
    public List<EmployeeEntity> findEmployeesOnLeaveByDate(LocalDate date) {
        try (Connection con = sql2o.open(); Query query = con.createQuery(FIND_ALL_DAILY)) {
            return query
                    .addParameter("DATE", date)
                    .setColumnMappings(EmployeeMapper.getMapping())
                    .executeAndFetch(EmployeeEntity.class);
        }
    }
}



