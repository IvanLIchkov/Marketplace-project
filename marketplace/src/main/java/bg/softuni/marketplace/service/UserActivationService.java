package bg.softuni.marketplace.service;

import bg.softuni.marketplace.model.events.UserRegisteredEvent;

public interface UserActivationService {

    void userRegistered(UserRegisteredEvent userRegisteredEvent);


    void cleanUpObsoleteActivationLinks();

    String createActivationCode(String userEmail);
}
