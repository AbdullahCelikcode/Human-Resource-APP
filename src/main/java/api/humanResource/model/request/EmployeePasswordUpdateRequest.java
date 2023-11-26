package api.humanResource.model.request;

public class EmployeePasswordUpdateRequest {
    private String username;
    private String oldPassword;
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
