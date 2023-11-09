package bg.softuni.marketplace.service.impl;

import bg.softuni.marketplace.repository.BlackListedRepository;
import org.springframework.stereotype.Service;

@Service
public class BlackListService {

    private final BlackListedRepository blackListedRepository;

    public BlackListService(BlackListedRepository blackListedRepository) {
        this.blackListedRepository = blackListedRepository;
    }

    public boolean isBlacklisted(String ipAddress){
        return blackListedRepository.findByIpAddress(ipAddress).isPresent();
    }
}
