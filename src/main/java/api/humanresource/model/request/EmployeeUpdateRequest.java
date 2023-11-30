package api.humanresource.model.request;

import api.humanresource.model.enums.Gender;
import api.humanresource.model.enums.Role;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class EmployeeUpdateRequest {

    @NotBlank
    private String firstname;
    @NotBlank
    private String lastname;
    @Valid
    @NotBlank
    private String email;
    @NotNull
    private Gender gender;
    @NotNull
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
