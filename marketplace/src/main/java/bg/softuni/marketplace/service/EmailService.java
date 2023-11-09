package bg.softuni.marketplace.service;

public interface EmailService {
    void sendRegistrationEmail(String userEmail, String username);
}
