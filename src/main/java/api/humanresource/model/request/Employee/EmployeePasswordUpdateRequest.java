package api.humanresource.model.request.Employee;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class EmployeePasswordUpdateRequest {


    @NotBlank
    @Size(min = 6,max =45)
    private String oldPassword;
    @NotBlank
    @Size(min = 6,max =45)
    private String newPassword;


    public String getOldPassword() {
        return oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }


}
