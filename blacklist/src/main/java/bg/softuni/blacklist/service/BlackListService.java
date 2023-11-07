package bg.softuni.blacklist.service;

import org.springframework.stereotype.Service;

@Service
public class BlackListService {

    public boolean isBlacklisted(String ipAddress){
        // TODO: create repository where admin may manage blacklisted IP-s
        return  true;
    }
}
