package veniamin.backend.atelie.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {

    CLIENT, CUTTER, MANAGER, ADMIN;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
