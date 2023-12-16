package api.humanresource.service.impl;

import api.humanresource.model.entity.EmployeeEntity;
import api.humanresource.repository.EmployeeRepository;
import api.humanresource.service.EmailService;
import api.humanresource.util.exception.GlobalException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    private final EmployeeRepository employeeRepository;

    public EmailServiceImpl(JavaMailSender mailSender, EmployeeRepository employeeRepository) {
        this.mailSender = mailSender;
        this.employeeRepository = employeeRepository;
    }


    @Override
    public void sendUsernameAndPasswordMail(EmployeeEntity employeeEntity) {

        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(employeeEntity.getEmail());
            helper.setSubject("Your Account Information");
            String mailContent = "Dear " + employeeEntity.getFirstname() + ",\n\n"
                    + "We are pleased to inform you that your account has been created successfully. Please find your login credentials below:\n\n"
                    + "Username: " + employeeEntity.getUsername() + "\n"
                    + "Password: " + employeeEntity.getPassword() + "\n\n"
                    + "Best regards,\n"
                    + "[Castilla of Developer]";
            helper.setText(mailContent);
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new GlobalException(e);
        }
    }

    @Override
    @Scheduled(cron = "0 0 9 * * ?")
    public void sendBirthdayEmail() {

        List<EmployeeEntity> employeesWithBirthdayToday = employeeRepository.findAll().stream()
                .filter(employee -> LocalDate.now().getMonth() == employee.getBirthday().getMonth()
                        && LocalDate.now().getDayOfMonth() == employee.getBirthday().getDayOfMonth())
                .toList();

        employeesWithBirthdayToday.forEach(employeeEntity -> {
            MimeMessage message = mailSender.createMimeMessage();
            try {
                MimeMessageHelper helper = new MimeMessageHelper(message, true);
                helper.setTo(employeeEntity.getEmail());
                helper.setSubject("HAPPY BIRTHDAY");
                String mailContent = "Dear " + employeeEntity.getFirstname() + ",\n\n"
                        + "It’s your special day today, and we couldn't let it pass without celebrating you! \n"
                        + "On behalf of the entire team at Castilla of Developer, we want to wish you a fantastic birthday.\n"
                        + "Happy Birthday! Best wishes from all of us,\n"
                        + "[Castilla of Developer]";
                helper.setText(mailContent);
                mailSender.send(message);
            } catch (MessagingException e) {
                throw new GlobalException(e);
            }
        });
    }
}
