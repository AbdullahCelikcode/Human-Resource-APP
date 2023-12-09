package api.humanresource.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class EmployeeLoginRequest {

    @NotBlank
    @Size(min = 2,max =45)
    private String username;
    @NotBlank
    @Size(min = 2,max =45)
    private String password;


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
