package api.humanResource.controller;

import api.humanResource.model.request.EmployeeLoginRequest;
import api.humanResource.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthController {
    private final EmployeeService employeeService;

    public AuthController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping("/login")
    public ResponseEntity<String> login(@RequestBody EmployeeLoginRequest employeeLoginRequest) {
        employeeService.login(employeeLoginRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
