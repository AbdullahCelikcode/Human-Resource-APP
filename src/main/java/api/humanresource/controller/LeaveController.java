package api.humanresource.controller;

import api.humanresource.model.request.LeaveCreateRequest;
import api.humanresource.model.request.LeaveStatusUpdateRequest;
import api.humanresource.model.response.LeaveResponse;
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
    public ResponseEntity<List<LeaveResponse>> getLeaves(@PathVariable @UUID String employeeId) {
        return ResponseEntity.ok(leaveService.findLeavesById(employeeId));

    }

    @GetMapping("/pending")
    public ResponseEntity<List<LeaveResponse>> getPendingLeaves() {
        return ResponseEntity.ok(leaveService.getPendingLeaves());
    }

    @GetMapping("/approved")
    public ResponseEntity<List<LeaveResponse>> getApprovedLeaves() {
        return ResponseEntity.ok(leaveService.getApprovedLeaves());
    }

    @GetMapping("/rejected")
    public ResponseEntity<List<LeaveResponse>> getRejectedLeaves() {
        return ResponseEntity.ok(leaveService.getRejectedLeaves());
    }

}
