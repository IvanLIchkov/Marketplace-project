package bg.softuni.marketplace.service.impl;

import bg.softuni.marketplace.model.domain.UserActivationLinkEntity;
import bg.softuni.marketplace.model.domain.UserEntity;
import bg.softuni.marketplace.model.events.UserRegisteredEvent;
import bg.softuni.marketplace.repository.UserActivationLinkRepository;
import bg.softuni.marketplace.repository.UserRepository;
import bg.softuni.marketplace.service.EmailService;
import bg.softuni.marketplace.service.UserActivationService;
import bg.softuni.marketplace.service.UserService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.Instant;
import java.util.NoSuchElementException;
import java.util.Random;

@Service
public class UserActivationServiceImpl implements UserActivationService {


    private static final String ACTIVATION_CODE_SYMBOLS = "abcdefghijklmnopqrstuvwxyzABCDEFGJKLMNPRSTUVWXYZ0123456789";
    private static final int ACTIVATION_CODE_LENGTH = 20;

    private final EmailService emailService;
    private final UserService userService;
    private final UserActivationLinkRepository userActivationLinkRepository;
    private final UserRepository userRepository;

    public UserActivationServiceImpl(EmailService emailService, UserService userService, UserActivationLinkRepository userActivationLinkRepository, UserRepository userRepository) {
        this.emailService = emailService;
        this.userService = userService;
        this.userActivationLinkRepository = userActivationLinkRepository;
        this.userRepository = userRepository;
    }

    @EventListener(UserRegisteredEvent.class)
    @Override
    public void userRegistered(UserRegisteredEvent event) {
        String activationCode = createActivationCode(event.getUserEmail());
        emailService.sendRegistrationEmail(event.getUserEmail(), event.getUsername(), activationCode);
    }

    @Override
    public void activateUser(String activationCode){
        UserActivationLinkEntity userActivationLinkEntity =
                this.userActivationLinkRepository.findByActivationLink(activationCode)
                        .orElseThrow(() -> new NoSuchElementException("Activation code not found may be expired!"));

        this.userRepository.save(userActivationLinkEntity.getUser().setConfirmedEmail(true));

    }

    @Override
    public void cleanUpObsoleteActivationLinks() {

    }

    @Override
    public String createActivationCode(String userEmail) {

        UserActivationLinkEntity userActivationLinkEntity = new UserActivationLinkEntity();
        String generatedCode = generateActivationCode();

        userActivationLinkEntity.setActivationLink(generatedCode);
        userActivationLinkEntity.setCreated(Instant.now());
        userActivationLinkEntity.setUser(userService.findUserByEmail(userEmail));

        userActivationLinkRepository.save(userActivationLinkEntity);

        return generatedCode;
    }

    private static String generateActivationCode(){
        StringBuilder activationCode  = new StringBuilder();
        Random random = new SecureRandom();

        for (int i = 0; i < ACTIVATION_CODE_LENGTH; i++) {
            int randIndex = random.nextInt(ACTIVATION_CODE_SYMBOLS.length());
            activationCode.append(ACTIVATION_CODE_SYMBOLS.charAt(randIndex));
        }

        return activationCode.toString();
    }
}
