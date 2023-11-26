package api.humanResource.controller;

import api.humanResource.model.request.EmployeeCreateRequest;
import api.humanResource.model.request.EmployeePasswordUpdateRequest;
import api.humanResource.model.request.EmployeeUpdateRequest;
import api.humanResource.model.response.EmployeesResponse;
import api.humanResource.service.EmployeeService;
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

    @PutMapping("/employee/{employeeId}")
    public void updateEmployee(@RequestBody EmployeeUpdateRequest employeeUpdateRequest, @PathVariable String employeeId) {
        employeeService.updateEmployee(employeeUpdateRequest,employeeId);
    }

    @PutMapping("/employee/password")
    public ResponseEntity<String> updatePassword(@RequestBody EmployeePasswordUpdateRequest employeeUpdateRequest)  {
        employeeService.updatePassword(employeeUpdateRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/employees")
    public List<EmployeesResponse> getAllEmployees() {
        return employeeService.getAllEmployees();
    }
}
