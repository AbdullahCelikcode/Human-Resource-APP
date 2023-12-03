package api.humanresource.controller;

import api.humanresource.model.request.EmployeeCreateRequest;
import api.humanresource.model.request.EmployeePasswordUpdateRequest;
import api.humanresource.model.request.EmployeeUpdateRequest;
import api.humanresource.model.response.EmployeesResponse;
import api.humanresource.service.EmployeeService;
import jakarta.validation.Valid;
import org.hibernate.validator.constraints.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
class EmployeeController {


    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/employee")
    public ResponseEntity<Void> createEmployee(@Valid @RequestBody EmployeeCreateRequest employeeCreateRequest) {
        employeeService.createEmployee(employeeCreateRequest);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/employee/{id}")
    public ResponseEntity<Void> updateEmployee(@PathVariable @UUID String id, @Valid @RequestBody EmployeeUpdateRequest employeeUpdateRequest) {
        employeeService.updateEmployee(id, employeeUpdateRequest);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/employee/password")
    public ResponseEntity<Void> updatePassword(@RequestBody @Valid EmployeePasswordUpdateRequest employeeUpdateRequest) {
        employeeService.updatePassword(employeeUpdateRequest);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/employees")
    public ResponseEntity<List<EmployeesResponse>> getAllEmployees() {

        return ResponseEntity.ok(employeeService.getAllEmployees());
    }
}
