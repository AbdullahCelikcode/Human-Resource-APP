package api.humanresource.service.impl;

import api.humanresource.model.entity.EmployeeEntity;
import api.humanresource.model.entity.LeaveEntity;
import api.humanresource.repository.EmployeeRepository;
import api.humanresource.service.EmailService;
import api.humanresource.service.EmployeeEmailService;
import api.humanresource.util.exception.GlobalException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
class EmployeeEmailServiceImpl implements EmployeeEmailService {


    private final EmailService emailService;

    private final EmployeeRepository employeeRepository;

    private final String companyName = "Castilla of Developer";

    public EmployeeEmailServiceImpl(EmailService emailService, EmployeeRepository employeeRepository) {
        this.emailService = emailService;
        this.employeeRepository = employeeRepository;

    }


    @Override
    public void sendUsernameAndPasswordMail(EmployeeEntity employeeEntity, String password) {

        String subject = "Your Account Information";
        String content = "Dear " + employeeEntity.getFirstname() + ",\n\n"
                + "We are pleased to inform you that your account has been created successfully. Please find your login credentials below:\n\n"
                + "Username: " + employeeEntity.getUsername() + "\n"
                + "Password: " + password + "\n\n"
                + "Best regards,\n"
                + companyName;
        emailService.send(employeeEntity.getEmail(), subject, content);
    }

    @Override
    // @Scheduled(cron = "0  9  * * *")       //sending every day at 9 pm
    //  @Scheduled(cron = " */15  *  *  *  * *") // sending every 15 seconds
    public void sendBirthdayEmail() {

        List<EmployeeEntity> employeesWithBirthdayToday = employeeRepository.findAll().stream()
                .filter(employee -> employee.getBirthday() != null)
                .filter(employee -> LocalDate.now().getMonth() == employee.getBirthday().getMonth()
                        && LocalDate.now().getDayOfMonth() == employee.getBirthday().getDayOfMonth())
                .toList();

        employeesWithBirthdayToday.forEach(employeeEntity -> {
            String subject = "HAPPY BIRTHDAY";
            String content = "Dear " + employeeEntity.getFirstname() + ",\n\n"
                    + "It’s your special day today, and we couldn't let it pass without celebrating you! \n"
                    + "On behalf of the entire team at Castilla of Developer, we want to wish you a fantastic birthday.\n"
                    + "Happy Birthday! Best wishes from all of us,\n"
                    + companyName;
            emailService.send(employeeEntity.getEmail(), subject, content);
        });
    }
}
