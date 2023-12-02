package bg.softuni.marketplace.service.impl;

import bg.softuni.marketplace.model.domain.BlackListedEntity;
import bg.softuni.marketplace.model.domain.UserEntity;
import bg.softuni.marketplace.repository.BlackListedRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class BlackListService {

    private final BlackListedRepository blackListedRepository;

    public BlackListService(BlackListedRepository blackListedRepository1) {
        this.blackListedRepository = blackListedRepository1;
    }

    public boolean isBlacklisted(String ipAddress){
        return blackListedRepository.findByIpAddress(ipAddress).isPresent();
    }

    public void banUser(UserEntity user) {
        BlackListedEntity blackListed = new BlackListedEntity()
                .setUser(user)
                .setUserName(user.getUsername())
                .setIpAddress(user.getIpAddress());
        this.blackListedRepository.save(blackListed);
    }

    @Transactional
    public void unbanUser(Long userId){
        this.blackListedRepository.deleteByUserId(userId);
    }
}
