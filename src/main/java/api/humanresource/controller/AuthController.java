package api.humanresource.controller;

import api.humanresource.model.request.EmployeeLoginRequest;
import api.humanresource.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/controller")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @GetMapping("/login")
    public ResponseEntity<String> login(@RequestBody EmployeeLoginRequest employeeLoginRequest) {
        authService.login(employeeLoginRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
