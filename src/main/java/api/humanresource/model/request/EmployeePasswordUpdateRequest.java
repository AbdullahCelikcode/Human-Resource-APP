package api.humanresource.model.request;

import jakarta.validation.constraints.NotNull;

public class EmployeePasswordUpdateRequest {
    @NotNull
    private String username;
    @NotNull
    private String oldPassword;
    @NotNull
    private String newPassword;

    public String getOldPassword() {
        return oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public String getUsername() {
        return username;
    }
}
