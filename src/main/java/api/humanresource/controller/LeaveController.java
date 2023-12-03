package api.humanresource.controller;

import api.humanresource.model.request.LeaveCreateRequest;
import api.humanresource.model.response.LeaveResponse;
import api.humanresource.service.LeaveService;
import jakarta.validation.Valid;
import org.hibernate.validator.constraints.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leave")
public class LeaveController {
    private final LeaveService leaveService;

    public LeaveController(LeaveService leaveService) {
        this.leaveService = leaveService;
    }

    @PostMapping("")
    public ResponseEntity<Void> takeLeave(@RequestBody @Valid LeaveCreateRequest leaveCreateRequest) {
        leaveService.add(leaveCreateRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<List<LeaveResponse>> getLeaves(@PathVariable @UUID String id) {
        return ResponseEntity.ok(leaveService.getLeaves(id));

    }

}
