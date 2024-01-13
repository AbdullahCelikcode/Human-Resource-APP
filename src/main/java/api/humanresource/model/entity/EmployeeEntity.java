package api.humanresource.model.entity;

import api.humanresource.model.enums.Gender;
import api.humanresource.model.enums.Role;
import api.humanresource.model.response.LeaveAllResponse;

import java.time.LocalDate;


public class EmployeeEntity {

    private String id;
    private String firstname;
    private String lastname;
    private String email;
    private Gender gender;
    private Role role;
    private LocalDate birthday;
    private String username;
    private String password;


    public LeaveAllResponse.Employee employeeEntityToEmployee() {
        LeaveAllResponse.Employee employee1 = new LeaveAllResponse.Employee();
        employee1.setEmployeeId(this.getId());
        employee1.setFirstName(this.getFirstname());
        employee1.setLastName(this.getLastname());
        return employee1;

    }

    public EmployeeEntity(String id, String firstname, String lastname, String email, Gender gender, Role role, LocalDate birthday, String username, String password) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.gender = gender;
        this.role = role;
        this.birthday = birthday;
        this.username = username;
        this.password = password;
    }


    public String getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public Gender getGender() {
        return gender;
    }

    public Role getRole() {
        return role;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
}
