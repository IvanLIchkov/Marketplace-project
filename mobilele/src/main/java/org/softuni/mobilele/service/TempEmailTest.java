package org.softuni.mobilele.service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TempEmailTest implements CommandLineRunner {

    private final EmailService emailService;

    public TempEmailTest(EmailService emailService) {
        this.emailService = emailService;
    }

    @Override
    public void run(String... args) throws Exception {
        emailService.sendRegistrationEmail("scopi@example.com", "Scopi");
    }
}
