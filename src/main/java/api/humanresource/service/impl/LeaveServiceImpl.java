package api.humanresource.service.impl;

import api.humanresource.model.entity.LeaveEntity;
import api.humanresource.model.enums.Status;
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
    public void create(LeaveCreateRequest leaveCreateRequest) {
        if (employeeRepository.findById(leaveCreateRequest.getEmployeeId()).isEmpty()) {
            throw new GlobalException("Employee is not exist");
        }
        if (isDublicated(leaveCreateRequest)) {
            throw new GlobalException("Leave already exist");
        }
        LeaveEntity leaveEntity = new LeaveEntity(
                leaveCreateRequest.getStartDate(),
                leaveCreateRequest.getFinishDate(),
                leaveCreateRequest.getType(),
                leaveCreateRequest.getExplanation(),
                Status.PENDING,
                leaveCreateRequest.getEmployeeId()
        );
        leaveRepository.save(leaveEntity);
    }

    private boolean isDublicated(LeaveCreateRequest leaveCreateRequest) {
        return leaveRepository.isExistByDate(
                leaveCreateRequest.getStartDate(),
                leaveCreateRequest.getFinishDate(),
                leaveCreateRequest.getEmployeeId());
    }


    @Override
    public List<LeaveResponse> findLeavesById(String employeeId) {
        if (employeeRepository.findById(employeeId).isEmpty()) {
            throw new GlobalException("Employee is not exist");
        }
        List<LeaveEntity> leaveEntities = leaveRepository.findLeavesById(employeeId);
        return leaveEntities.stream()
                .map(leaveEntity -> new LeaveResponse(
                        leaveEntity.getId(),
                        leaveEntity.getStartDate(),
                        leaveEntity.getFinishDate(),
                        leaveEntity.getExplanation(),
                        leaveEntity.getStatus(),
                        leaveEntity.getType()))
                .toList();
    }


}
