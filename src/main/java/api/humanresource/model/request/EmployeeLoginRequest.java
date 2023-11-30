package api.humanresource.model.request;

import jakarta.validation.constraints.NotNull;

public class EmployeeLoginRequest {
    @NotNull
    private String username;
    @NotNull
    private String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
