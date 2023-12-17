package api.humanresource.service;

public interface EmailService {
    void send(String email, String subject, String content);
}
