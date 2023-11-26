package api.humanResource.model.request;

import api.humanResource.model.enums.Gender;
import api.humanResource.model.enums.Role;
import api.humanResource.util.annotations.GenderValidation;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


public class EmployeeCreateRequest {
    @NotBlank(message = "Firstname cannot be null")
    private String firstname;
    @NotBlank(message = "Lastname cannot be null")
    private String lastname;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email cannot be null")
    private String email;

    @GenderValidation()
    private Gender gender;
    private Role role;

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
}
