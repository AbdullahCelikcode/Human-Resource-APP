package api.humanresource.controller;

import api.humanresource.model.request.LeaveCreateRequest;
import api.humanresource.model.response.LeaveResponse;
import api.humanresource.service.LeaveService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LeaveController {
    private final LeaveService leaveService;

    public LeaveController(LeaveService leaveService) {
        this.leaveService = leaveService;
    }

    @PostMapping("/leave")
    public void takeLeave(@RequestBody @Valid LeaveCreateRequest leaveCreateRequest) {
        leaveService.add(leaveCreateRequest);
    }

    @GetMapping("/leave/{id}")
    public List<LeaveResponse> getLeaves(@PathVariable String id) {
        return leaveService.getLeaves(id);

    }

}
