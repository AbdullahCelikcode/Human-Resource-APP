package api.humanresource.controller;

import api.humanresource.model.request.employee.EmployeeLoginRequest;
import api.humanresource.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/auth")
class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody @Valid EmployeeLoginRequest employeeLoginRequest) {
        authService.login(employeeLoginRequest);
        return ResponseEntity.ok().build();
    }
}
