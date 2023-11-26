package api.humanResource.model.request;

import api.humanResource.model.enums.Gender;
import api.humanResource.model.enums.Role;

public class EmployeeUpdateRequest {
    // firstname-lastname-email-gender-role-username-password

    private String firstname;
    private String lastname;
    private String email;
    private Gender gender;
    private Role role;
    private String username;
    private String password;


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
}
