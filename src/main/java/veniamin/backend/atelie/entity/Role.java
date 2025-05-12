package veniamin.backend.atelie.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {

    ROLE_CLIENT, ROLE_CUTTER, ROLE_MANAGER, ROLE_ADMIN;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
