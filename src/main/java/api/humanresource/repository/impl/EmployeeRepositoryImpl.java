package api.humanresource.repository.impl;

import api.humanresource.model.entity.EmployeeEntity;
import api.humanresource.model.mappers.EmployeeMapper;
import api.humanresource.repository.EmployeeRepository;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import java.util.List;


@Repository
class EmployeeRepositoryImpl implements EmployeeRepository {
    private  static final String INSERT_QUERY = "INSERT INTO EMPLOYEE (ID,FIRST_NAME,LAST_NAME,EMAIL,GENDER,ROLE,USERNAME,PASSWORD ) " +
            "VALUES (:id,:firstname,:lastname,:email,:gender,:role,:username,:password)";
    private static final String UPDATE_QUERY =
            "UPDATE EMPLOYEE SET FIRST_NAME=:firstname,LAST_NAME=:lastname,EMAIL=:email,GENDER=:gender,ROLE=:role," +
                    "USERNAME=:username,PASSWORD=:password WHERE ID=:id ";
    private  static final String FIND_BY_ID_QUERY = "SELECT ID, FIRST_NAME, LAST_NAME, EMAIL, GENDER, ROLE ,USERNAME, PASSWORD FROM EMPLOYEE WHERE id=:id ";

    private static final String FIND_BY_USERNAME_QUERY = "SELECT ID, FIRST_NAME, LAST_NAME, EMAIL, GENDER, ROLE ,USERNAME, PASSWORD FROM EMPLOYEE WHERE USERNAME=:username ";

    private  static final String FIND_BY_EMAIL_QUERY = "SELECT ID, FIRST_NAME, LAST_NAME, EMAIL, GENDER, ROLE ,USERNAME, PASSWORD FROM EMPLOYEE WHERE EMAIL=:email ";

    private static final String FIND_ALL_QUERY = "SELECT ID, FIRST_NAME, LAST_NAME, EMAIL, GENDER, ROLE ,USERNAME, PASSWORD FROM EMPLOYEE ";


    private final Sql2o sql2o;


    public EmployeeRepositoryImpl(Sql2o sql2o) {
        this.sql2o = sql2o;

    }


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
                    .addParameter(EmployeeMapper.USERNAME.getField(), employeeEntity.getUsername())
                    .addParameter(EmployeeMapper.PASSWORD.getField(), employeeEntity.getPassword())
                    .executeUpdate();


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
                    .addParameter(EmployeeMapper.USERNAME.getField(), employeeEntity.getUsername())
                    .addParameter(EmployeeMapper.PASSWORD.getField(), employeeEntity.getPassword())
                    .executeUpdate();

        }
    }

    @Override
    public EmployeeEntity findById(String employeeId) {

        try (Connection con = sql2o.open(); Query query = con.createQuery(FIND_BY_ID_QUERY)) {
            return query
                    .addParameter(EmployeeMapper.ID.getField(), employeeId)
                    .setColumnMappings(EmployeeMapper.getMapping())
                    .executeAndFetchFirst(EmployeeEntity.class);
        }

    }


    @Override
    public EmployeeEntity findByUsername(String username) {
        try (Connection con = sql2o.open(); Query query = con.createQuery(FIND_BY_USERNAME_QUERY)) {
            return query
                    .addParameter(EmployeeMapper.USERNAME.getField(), username)
                    .setColumnMappings(EmployeeMapper.getMapping())
                    .executeAndFetchFirst(EmployeeEntity.class);
        }
    }

    @Override
    public EmployeeEntity findByEmail(String email) {
        try (Connection con = sql2o.open(); Query query = con.createQuery(FIND_BY_EMAIL_QUERY)) {
            return query
                    .addParameter(EmployeeMapper.EMAIL.getField(), email)
                    .setColumnMappings(EmployeeMapper.getMapping())
                    .executeAndFetchFirst(EmployeeEntity.class);
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
}



