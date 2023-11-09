package bg.softuni.marketplace.service.impl;

import bg.softuni.marketplace.model.events.UserRegisteredEvent;
import bg.softuni.marketplace.service.EmailService;
import bg.softuni.marketplace.service.UserActivationService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class UserActivationServiceImpl implements UserActivationService {

    private final EmailService emailService;

    public UserActivationServiceImpl(EmailService emailService) {
        this.emailService = emailService;
    }

    @EventListener(UserRegisteredEvent.class)
    @Override
    public void userRegistered(UserRegisteredEvent event) {
        emailService.sendRegistrationEmail(event.getUserEmail(), event.getUsername());
    }
}
