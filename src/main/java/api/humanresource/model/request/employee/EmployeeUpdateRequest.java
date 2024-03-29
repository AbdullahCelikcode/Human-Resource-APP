package api.humanresource.model.request.employee;

import api.humanresource.model.enums.Gender;
import api.humanresource.model.enums.Role;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class EmployeeUpdateRequest {

    @NotBlank
    @Size(min = 2,max =45)
    private String firstname;
    @NotBlank
    @Size(min = 2,max =45)
    private String lastname;
    @Valid
    @NotBlank
    @Size(min = 2,max =45)
    private String email;
    @NotNull
    private Gender gender;
    @NotNull
    private Role role;
    @Past(message = "Birthday cannot be future")
    @NotNull
    private LocalDate birthday;



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
    public LocalDate getBirthday() {return birthday;}
}
