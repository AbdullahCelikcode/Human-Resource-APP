package api.humanResource.model.entity;

import api.humanResource.model.enums.Gender;
import api.humanResource.model.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


public class EmployeeEntity {

    @NotBlank(message = "Id cannot be null")
    private String id;
    @NotBlank(message = "Firstname cannot be null")
    private String firstname;
    @NotBlank(message = "Lastname cannot be null")
    private String lastname;
    @Email(message = "Email should be valid")
    @NotBlank
    private String email;

    private Gender gender;
    private Role role;
    @NotBlank(message = "Username cannot be null")
    private String username;
    @NotBlank(message = "Password cannot be null")
    private String password;

    public EmployeeEntity(String id, String firstname, String lastname, String email, Gender gender, Role role, String username, String password) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.gender = gender;
        this.role = role;
        this.username = username;
        this.password = password;
    }

    public EmployeeEntity() {

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
}
