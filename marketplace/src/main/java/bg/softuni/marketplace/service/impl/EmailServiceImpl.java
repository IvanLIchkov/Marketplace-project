package bg.softuni.marketplace.service.impl;

import bg.softuni.marketplace.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class EmailServiceImpl implements EmailService {

    private final TemplateEngine templateEngine;
    private final JavaMailSender javaMailSender;
    private final String siteMail;

    public EmailServiceImpl(TemplateEngine templateEngine, JavaMailSender javaMailSender,
                            @Value("${mail.marketplace}") String siteMail) {
        this.templateEngine = templateEngine;
        this.javaMailSender = javaMailSender;
        this.siteMail = siteMail;
    }


    @Override
    public void sendRegistrationEmail(String userEmail, String username, String activationLink)  {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);

        try {
            mimeMessageHelper.setTo(userEmail);
            mimeMessageHelper.setFrom(siteMail);
            mimeMessageHelper.setReplyTo(siteMail);
            mimeMessageHelper.setSubject("Welcome to marketplace!");
            mimeMessageHelper.setText(generateRegistrationEmailBody(username, activationLink), true);

            javaMailSender.send(mimeMessageHelper.getMimeMessage());
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateRegistrationEmailBody(String username, String activationLink){
        Context context = new Context();
        context.setVariable("username", username);
        context.setVariable("activation_link", activationLink);
        return templateEngine.process("email/registration-email.html", context);
    }
}
