package bg.softuni.marketplace.service.schedulers;

import bg.softuni.marketplace.service.UserActivationService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ActivationLinkCleanupScheduler {

    private final UserActivationService userActivationService;

    public ActivationLinkCleanupScheduler(UserActivationService userActivationService) {
        this.userActivationService = userActivationService;
    }

    @Scheduled(cron = "0 */12 * * *")
    public void cleanUp(){
        userActivationService.cleanUpObsoleteActivationLinks();
    }
}
