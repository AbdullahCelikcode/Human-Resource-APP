package api.humanresource.service.impl;

import api.humanresource.model.entity.LeaveEntity;
import api.humanresource.model.request.LeaveCreateRequest;
import api.humanresource.model.response.LeaveResponse;
import api.humanresource.repository.EmployeeRepository;
import api.humanresource.repository.LeaveRepository;
import api.humanresource.service.LeaveService;
import api.humanresource.util.exception.GlobalException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class LeaveServiceImpl implements LeaveService {
    private final LeaveRepository leaveRepository;
    private final EmployeeRepository employeeRepository;

    public LeaveServiceImpl(LeaveRepository leaveRepository, EmployeeRepository employeeRepository) {
        this.leaveRepository = leaveRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void add(LeaveCreateRequest leaveCreateRequest) {
        if (employeeRepository.findById(leaveCreateRequest.getEmployeeId()).isEmpty()) {
            throw new GlobalException("Employee is not exist");
        }
        LeaveEntity leaveEntity = new LeaveEntity(
                leaveCreateRequest.getStartDate(),
                leaveCreateRequest.getFinishDate(),
                leaveCreateRequest.getType(),
                leaveCreateRequest.getExplanation(),
                leaveCreateRequest.getEmployeeId()
        );
        leaveRepository.save(leaveEntity);
    }

    @Override
    public List<LeaveResponse> getLeaves(String id) {
        if (employeeRepository.findById(id).isEmpty()) {
            throw new GlobalException("Employee is not exist");
        }
        List<LeaveEntity> leaveEntities = leaveRepository.getLeaves(id);
        return leaveEntities.stream()
                .map(leaveEntity -> new LeaveResponse(leaveEntity.getStartDate(), leaveEntity.getFinishDate(), leaveEntity.getType()))
                .toList();
    }
}
