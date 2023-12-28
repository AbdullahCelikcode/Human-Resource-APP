package api.humanresource.service.impl;

import api.humanresource.model.entity.EmployeeEntity;
import api.humanresource.model.entity.LeaveEntity;
import api.humanresource.model.enums.LeaveStatus;
import api.humanresource.model.request.LeaveCreateRequest;
import api.humanresource.model.request.LeaveStatusUpdateRequest;
import api.humanresource.model.response.EmployeesResponse;
import api.humanresource.model.response.LeaveAllResponse;
import api.humanresource.model.response.LeaveEmployeeResponse;
import api.humanresource.repository.EmployeeRepository;
import api.humanresource.repository.LeaveRepository;
import api.humanresource.service.EmployeeEmailService;
import api.humanresource.service.LeaveService;
import api.humanresource.util.exception.GlobalException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
class LeaveServiceImpl implements LeaveService {

    private final LeaveRepository leaveRepository;
    private final EmployeeRepository employeeRepository;

    private final EmployeeEmailService employeeEmailService;

    public LeaveServiceImpl(LeaveRepository leaveRepository, EmployeeRepository employeeRepository, EmployeeEmailService employeeEmailService) {
        this.leaveRepository = leaveRepository;
        this.employeeRepository = employeeRepository;
        this.employeeEmailService = employeeEmailService;
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
                LeaveStatus.PENDING,
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
    public void updateStatus(LeaveStatusUpdateRequest leaveStatusUpdateRequest) {
        LeaveEntity leaveEntity = leaveRepository.findLeavesById(leaveStatusUpdateRequest.getId()).
                orElseThrow(() -> new GlobalException("Leave Is Not Exist"));
        leaveEntity.setStatus(leaveStatusUpdateRequest.getStatus());
        leaveRepository.update(leaveEntity);
        employeeEmailService.sendLeaveStatusChange(leaveEntity);
    }


    @Override
    public List<LeaveEmployeeResponse> findLeavesByEmployeeId(String employeeId) {
        if (employeeRepository.findById(employeeId).isEmpty()) {
            throw new GlobalException("Employee is not exist");
        }
        List<LeaveEntity> leaveEntities = leaveRepository.findLeavesByEmployeeId(employeeId);
        return leaveEntities.stream()
                .map(leaveEntity -> new LeaveEmployeeResponse(
                        leaveEntity.getId(),
                        leaveEntity.getStartDate(),
                        leaveEntity.getFinishDate(),
                        leaveEntity.getExplanation(),
                        leaveEntity.getStatus(),
                        leaveEntity.getType()))
                .toList();
    }


    @Override
    public List<LeaveAllResponse> getLeavesByStatus(LeaveStatus leaveStatus) {
        List<LeaveEntity> leaveEntities = leaveRepository.findLeavesByStatus(leaveStatus);
        return leaveEntities.stream().map(leaveEntity -> new LeaveAllResponse(
                        leaveEntity.getId(),
                        leaveEntity.getStartDate(),
                        leaveEntity.getFinishDate(),
                        leaveEntity.getExplanation(),
                        leaveEntity.getStatus(),
                        leaveEntity.getType(),
                        leaveEntity.getEmployeeId()))
                .toList();
    }

    @Override
    public List<EmployeesResponse> getEmployeesOnLeave() {
        List<EmployeeEntity> employeeEntities = employeeRepository.findEmployeesOnLeaveByDate(LocalDate.now());
        return employeeEntities.stream()
                .map(employeeEntity -> new EmployeesResponse(
                        employeeEntity.getId(),
                        employeeEntity.getFirstname(),
                        employeeEntity.getLastname(),
                        employeeEntity.getEmail(),
                        employeeEntity.getGender(),
                        employeeEntity.getBirthday(),
                        employeeEntity.getRole(),
                        employeeEntity.getUsername()))
                .toList();
    }
}



