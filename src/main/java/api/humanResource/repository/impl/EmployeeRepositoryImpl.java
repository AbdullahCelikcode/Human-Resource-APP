package api.humanResource.repository.impl;

import api.humanResource.model.entity.EmployeeEntity;
import api.humanResource.repository.EmployeeRepository;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;


@Repository
class EmployeeRepositoryImpl implements EmployeeRepository {
    // QUERY'S

    private static final String insertQuery = "INSERT INTO EMPLOYEE ( ID, FIRST_NAME, LAST_NAME, EMAIL, GENDER, ROLE, USERNAME, PASSWORD ) " +
            "VALUES ( :id, :firstname, :lastname, :email, :gender,:role, :username, :password)";
    private static final String updateQuery =
            "UPDATE EMPLOYEE SET  FIRST_NAME=:firstname, LAST_NAME=:lastname, EMAIL=:email, GENDER=:gender, ROLE=:role, " +
                    "USERNAME=:username, PASSWORD=:password WHERE ID=:id ";
    private static final String findByIdQuery = "SELECT id, FIRST_NAME, LAST_NAME, EMAIL, GENDER, ROLE ,USERNAME, PASSWORD FROM EMPLOYEE WHERE id=:id ";

    private static final String findByUsernameQuery = "SELECT id, FIRST_NAME, LAST_NAME, EMAIL, GENDER, ROLE ,USERNAME, PASSWORD FROM EMPLOYEE WHERE USERNAME=:username ";
    private static final String findAllEmailsQuery = "SELECT EMAIL FROM EMPLOYEE ";         //sorulacak!!!
    private static final String findALlUsernamesQuery = "SELECT USERNAME FROM EMPLOYEE "; // Böyle hepsini aramak yerine 1 kere olanı aramak daha mantıklı değil mi ?
    private static final String findAllQuery = "SELECT ID, FIRST_NAME, LAST_NAME, EMAIL, GENDER, ROLE ,USERNAME, PASSWORD FROM EMPLOYEE ";

    private final Sql2o sql2o;

    public EmployeeRepositoryImpl(Sql2o sql2o) {
        this.sql2o = sql2o;
    }


    @Override
    public void createEmployee(EmployeeEntity employeeEntity) {


        try (Connection con = sql2o.beginTransaction()) {
            con.createQuery(insertQuery)
                    .addParameter("id", employeeEntity.getId())
                    .addParameter("firstname", employeeEntity.getFirstname())
                    .addParameter("lastname", employeeEntity.getLastname())
                    .addParameter("email", employeeEntity.getEmail())
                    .addParameter("gender", employeeEntity.getGender())
                    .addParameter("role", employeeEntity.getRole())
                    .addParameter("username", employeeEntity.getUsername())
                    .addParameter("password", employeeEntity.getPassword())
                    .executeUpdate();
            con.commit();

        }
    }


    @Override
    public void updateEmployee(EmployeeEntity employeeEntity) {

        try (Connection con = sql2o.beginTransaction()) {
            con.createQuery(updateQuery)
                    .addParameter("id", employeeEntity.getId())
                    .addParameter("firstname", employeeEntity.getFirstname())
                    .addParameter("lastname", employeeEntity.getLastname())
                    .addParameter("email", employeeEntity.getEmail())
                    .addParameter("gender", employeeEntity.getGender())
                    .addParameter("role", employeeEntity.getRole())
                    .addParameter("username", employeeEntity.getUsername())
                    .addParameter("password", employeeEntity.getPassword())
                    .executeUpdate();
            con.commit();
        }
    }


    public EmployeeEntity findById(String employeeId) {

        try (Connection con = sql2o.open()) {
            return con.createQuery(findByIdQuery)
                    .addParameter("id", employeeId)
                    .addColumnMapping("id", "id")
                    .addColumnMapping("FIRST_NAME", "firstname")
                    .addColumnMapping("LAST_NAME", "lastname")
                    .addColumnMapping("EMAIL", "email")
                    .addColumnMapping("GENDER", "gender")
                    .addColumnMapping("ROLE", "role")
                    .addColumnMapping("USERNAME", "username")
                    .addColumnMapping("PASSWORD", "password")
                    .executeAndFetchFirst(EmployeeEntity.class);
        }

    }

    @Override
    public List<String> findAllEmails() {
        Connection con = sql2o.open();

        try {
            return con.createQuery(findAllEmailsQuery).executeScalarList(String.class);

        } finally {
            con.close();
        }
    }

    @Override
    public EmployeeEntity findByUsername(String username) {
        try (Connection con = sql2o.open()) {
            return con.createQuery(findByUsernameQuery)
                    .addParameter("username", username)
                    .addColumnMapping("id", "id")
                    .addColumnMapping("FIRST_NAME", "firstname")
                    .addColumnMapping("LAST_NAME", "lastname")
                    .addColumnMapping("EMAIL", "email")
                    .addColumnMapping("GENDER", "gender")
                    .addColumnMapping("ROLE", "role")
                    .addColumnMapping("USERNAME", "username")
                    .addColumnMapping("PASSWORD", "password")
                    .executeAndFetchFirst(EmployeeEntity.class);
        }
    }

    @Override
    public List<String> findAllUsernames() {


        try (Connection con = sql2o.open()) {
            return con.createQuery(findALlUsernamesQuery).executeScalarList(String.class);

        }

    }

    @Override
    public List<EmployeeEntity> findAll() {

        try (Connection con = sql2o.open()) {
            return con.createQuery(findAllQuery)
                    .addColumnMapping("ID", "id")
                    .addColumnMapping("FIRST_NAME", "firstname")
                    .addColumnMapping("LAST_NAME", "lastname")
                    .addColumnMapping("EMAIL", "email")
                    .addColumnMapping("GENDER", "gender")
                    .addColumnMapping("ROLE", "role")
                    .addColumnMapping("USERNAME", "username")
                    .addColumnMapping("PASSWORD", "password")
                    .executeAndFetch(EmployeeEntity.class);
        }
    }
}



