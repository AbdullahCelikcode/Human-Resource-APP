package api.humanresource.service.impl;

import api.humanresource.model.entity.EmployeeEntity;
import api.humanresource.model.entity.LeaveEntity;
import api.humanresource.repository.EmployeeRepository;
import api.humanresource.service.EmailService;
import api.humanresource.service.LeaveEmailService;
import api.humanresource.util.exception.GlobalException;
import org.springframework.stereotype.Service;

@Service
public class LeaveEmailServiceImpl implements LeaveEmailService {
    private final EmployeeRepository employeeRepository;
    private final EmailService emailService;

    private final String email = "humanresource11134@gmail.com";

    public LeaveEmailServiceImpl(EmployeeRepository employeeRepository, EmailService emailService) {
        this.employeeRepository = employeeRepository;
        this.emailService = emailService;
    }

    @Override
    public void sendLeaveStatusChange(LeaveEntity leaveEntity) {
        EmployeeEntity employeeEntity = employeeRepository.findById(leaveEntity.getEmployeeId()).orElseThrow(() -> new GlobalException("Employee Is Not Exist"));
        String subject = "Your Leave Information";
        String content = "Dear " + employeeEntity.getFirstname() + ",\n\n"
                + "We are pleased to inform you that your leave request is " + leaveEntity.getStatus().toString().toLowerCase() + "\n\n"
                + "Best regards,\n"
                + "Castilla of Developer";
        emailService.send(email, subject, content);
    }
}
