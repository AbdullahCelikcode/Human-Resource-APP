package api.humanresource.controller;

import api.humanresource.model.enums.LeaveStatus;
import api.humanresource.model.request.LeaveCreateRequest;
import api.humanresource.model.request.LeaveStatusUpdateRequest;
import api.humanresource.model.response.EmployeesResponse;
import api.humanresource.model.response.LeaveAllResponse;
import api.humanresource.model.response.LeaveEmployeeResponse;
import api.humanresource.service.LeaveService;
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
@RequestMapping("/api/leave")
class LeaveController {

    private final LeaveService leaveService;

    public LeaveController(LeaveService leaveService) {
        this.leaveService = leaveService;
    }


    @PostMapping
    public ResponseEntity<Void> createLeave(@RequestBody @Valid LeaveCreateRequest leaveCreateRequest) {
        leaveService.create(leaveCreateRequest);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/status")
    public ResponseEntity<Void> updateStatus(@RequestBody @Valid LeaveStatusUpdateRequest leaveStatusUpdateRequest) {
        leaveService.updateStatus(leaveStatusUpdateRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<LeaveEmployeeResponse>> getLeaves(@PathVariable @UUID String employeeId) {

        return ResponseEntity.ok(leaveService.findLeavesByEmployeeId(employeeId));

    }

    @GetMapping("/pending")
    public ResponseEntity<List<LeaveAllResponse>> getPendingLeaves() {
        return ResponseEntity.ok(leaveService.getLeavesByStatus(LeaveStatus.PENDING));
    }

    @GetMapping("/approved")
    public ResponseEntity<List<LeaveAllResponse>> getApprovedLeaves() {
        return ResponseEntity.ok(leaveService.getLeavesByStatus(LeaveStatus.APPROVED));
    }

    @GetMapping("/rejected")
    public ResponseEntity<List<LeaveAllResponse>> getRejectedLeaves() {
        return ResponseEntity.ok(leaveService.getLeavesByStatus(LeaveStatus.REJECTED));
    }

    @GetMapping("/daily")
    public ResponseEntity<List<EmployeesResponse>> getEmployeesOnLeave() {
        return ResponseEntity.ok(leaveService.getEmployeesOnLeave());
    }
}
