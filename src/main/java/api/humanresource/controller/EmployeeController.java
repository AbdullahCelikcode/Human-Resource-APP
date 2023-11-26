package api.humanresource.controller;

import api.humanresource.model.request.EmployeeCreateRequest;
import api.humanresource.model.request.EmployeePasswordUpdateRequest;
import api.humanresource.model.request.EmployeeUpdateRequest;
import api.humanresource.model.response.EmployeesResponse;
import api.humanresource.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
class EmployeeController {


    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/employee")
    public void createEmployee(@Valid @RequestBody EmployeeCreateRequest employeeCreateRequest) {
        employeeService.createEmployee(employeeCreateRequest);
    }

    @PutMapping("/employee/{id}")
    public void updateEmployee(@PathVariable String id, @RequestBody EmployeeUpdateRequest employeeUpdateRequest) {
        employeeService.updateEmployee(id, employeeUpdateRequest);
    }

    @PutMapping("/employee/password")
    public ResponseEntity<String> updatePassword(@RequestBody EmployeePasswordUpdateRequest employeeUpdateRequest) {
        employeeService.updatePassword(employeeUpdateRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/employees")
    public List<EmployeesResponse> getAllEmployees() {
        return employeeService.getAllEmployees();
    }
}
