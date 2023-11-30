package api.humanresource.service.impl;

import api.humanresource.model.entity.LeaveEntity;
import api.humanresource.model.request.LeaveCreateRequest;
import api.humanresource.model.response.LeaveResponse;
import api.humanresource.repository.LeaveRepository;
import api.humanresource.service.LeaveService;
import api.humanresource.util.exception.GlobalException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveServiceImpl implements LeaveService {
    private final LeaveRepository leaveRepository;

    public LeaveServiceImpl(LeaveRepository leaveRepository) {
        this.leaveRepository = leaveRepository;
    }

    @Override
    public void add(LeaveCreateRequest leaveCreateRequest) {
        if (leaveCreateRequest.getFinishDate().before(leaveCreateRequest.getStartDate())) {
            throw new GlobalException("finish date  can not before start date");
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
        List<LeaveEntity> leaveEntities = leaveRepository.getLeaves(id);
        return leaveEntities.stream()
                .map(leaveEntity -> new LeaveResponse(leaveEntity.getStartDate(), leaveEntity.getFinishDate(), leaveEntity.getType()))
                .toList();


    }
}
