package api.humanresource.service.impl;

import api.humanresource.model.entity.EmployeeEntity;
import api.humanresource.model.entity.LeaveEntity;
import api.humanresource.model.enums.LeaveStatus;
import api.humanresource.model.request.Leave.LeaveCreateRequest;
import api.humanresource.model.request.Leave.LeavePaginationAndFilterRequest;
import api.humanresource.model.request.Leave.LeaveStatusUpdateRequest;
import api.humanresource.model.request.PaginationRequest;
import api.humanresource.model.response.EmployeesResponse;
import api.humanresource.model.response.LeaveAllResponse;
import api.humanresource.model.response.LeaveEmployeeResponse;
import api.humanresource.repository.EmployeeRepository;
import api.humanresource.repository.LeaveRepository;
import api.humanresource.service.LeaveEmailService;
import api.humanresource.service.LeaveService;
import api.humanresource.util.exception.GlobalException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
class LeaveServiceImpl implements LeaveService {

    private final LeaveRepository leaveRepository;
    private final EmployeeRepository employeeRepository;

    private final LeaveEmailService leaveEmailService;

    public LeaveServiceImpl(LeaveRepository leaveRepository, EmployeeRepository employeeRepository, LeaveEmailService leaveEmailService) {
        this.leaveRepository = leaveRepository;
        this.employeeRepository = employeeRepository;
        this.leaveEmailService = leaveEmailService;
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
        try {
            leaveRepository.save(leaveEntity);
            leaveEmailService.sendLeaveStatusChange(leaveEntity);
        } catch (RuntimeException e) {
            throw new GlobalException(e.getMessage());
        }
    }

    private boolean isDublicated(LeaveCreateRequest leaveCreateRequest) {
        return leaveRepository.isExistByDate(
                leaveCreateRequest.getStartDate(),
                leaveCreateRequest.getFinishDate(),
                leaveCreateRequest.getEmployeeId());
    }

    @Override
    public void updateStatus(LeaveStatusUpdateRequest leaveStatusUpdateRequest) {
        LeaveEntity leaveEntity = leaveRepository.findLeavesById(leaveStatusUpdateRequest.getId())
                .filter(leaveEntity1 -> leaveEntity1.getStatus().equals(leaveStatusUpdateRequest.getLeaveStatus()))
                .orElseThrow(() -> new GlobalException("Leave Is Not Exist"));
        leaveEntity.setStatus(leaveStatusUpdateRequest.getLeaveStatus());
        leaveRepository.update(leaveEntity);
        leaveEmailService.sendLeaveStatusChange(leaveEntity);
    }


    @Override
    public List<LeaveEmployeeResponse> findLeavesByEmployeeId(String employeeId, LeavePaginationAndFilterRequest leavePaginationAndFilterRequest) {
        if (employeeRepository.findById(employeeId).isEmpty()) {
            throw new GlobalException("Employee is not exist");
        }

        List<LeaveEntity> leaveEntities = leaveRepository.findLeavesByEmployeeId(employeeId,
                leavePaginationAndFilterRequest.getPaginationRequest().getPageNumber(),
                leavePaginationAndFilterRequest.getPaginationRequest().getPageSize(),
                leavePaginationAndFilterRequest.getFilter());


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
    public List<LeaveAllResponse> getLeavesByStatus(LeaveStatus leaveStatus, PaginationRequest paginationRequest) {
        List<LeaveEntity> leaveEntities = leaveRepository.findLeavesByStatus(leaveStatus,
                paginationRequest.getPageNumber(),
                paginationRequest.getPageSize());

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



