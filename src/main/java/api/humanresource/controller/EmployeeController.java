package api.humanresource.controller;

import api.humanresource.model.request.EmployeeCreateRequest;
import api.humanresource.model.request.EmployeePasswordUpdateRequest;
import api.humanresource.model.request.EmployeeUpdateRequest;
import api.humanresource.model.response.EmployeesResponse;
import api.humanresource.service.EmployeeEmailService;
import api.humanresource.service.EmployeeService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.hibernate.validator.constraints.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api")
class EmployeeController {

    private final EmployeeService employeeService;
    private final EmployeeEmailService employeeEmailService;

    public EmployeeController(EmployeeService employeeService, EmployeeEmailService employeeEmailService) {
        this.employeeService = employeeService;
        this.employeeEmailService = employeeEmailService;
    }


    @PostMapping("/employee")
    public ResponseEntity<Void> createEmployee(@RequestBody @Valid EmployeeCreateRequest employeeCreateRequest) {
        employeeService.createEmployee(employeeCreateRequest);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/employee/{id}")
    public ResponseEntity<Void> updateEmployee(@PathVariable @UUID String id, @RequestBody @Valid EmployeeUpdateRequest employeeUpdateRequest) {
        employeeService.updateEmployee(id, employeeUpdateRequest);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/employee/password")
    public ResponseEntity<Void> updatePassword(@PathVariable @UUID String id, @RequestBody @Valid EmployeePasswordUpdateRequest employeeUpdateRequest) {
        employeeService.updatePassword(id,employeeUpdateRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/employees")
    public ResponseEntity<List<EmployeesResponse>> getAllEmployees() {

        return ResponseEntity.ok(employeeService.getAllEmployees());
    }
    @GetMapping("/birthday")
    public void getBirthday() throws MessagingException {
        employeeEmailService.sendBirthdayEmail();
    }
}
