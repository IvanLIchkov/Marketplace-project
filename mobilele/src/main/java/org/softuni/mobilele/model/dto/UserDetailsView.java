package org.softuni.mobilele.model.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class UserDetailsView extends User {

    public UserDetailsView(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }


}
