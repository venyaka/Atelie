package veniamin.backend.atelie.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private String email;

    @Column
    private String password;

    @Column(name = "is_email_verificated")
    private Boolean isEmailVerificated;

    @Column(name = "token")
    private String token;

    @Column(name = "refresh_token")
    private String refreshToken;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "is_del")
    private Boolean isDeleted;

    @Column(name = "date_create")
    private LocalDateTime dateCreate;

    @Column(name = "date_update")
    private LocalDateTime dateUpdate;

    @Column(name = "is_suspicious")
    private Boolean isSuspicious;

    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @Column(name = "user_role")
    private Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<UserDeniedTable> deniedTables;

    @PrePersist
    public void prePersist() {
        this.isActive = Boolean.TRUE;
        this.isDeleted = Boolean.FALSE;
        this.isEmailVerificated = Boolean.FALSE;
        this.dateCreate = LocalDateTime.now();
        this.isSuspicious = Boolean.FALSE;
        if (roles.isEmpty()) {
            roles.add(Role.ROLE_CLIENT);
        }
    }

    public boolean isTableDenied(String tableName) {
        return deniedTables != null &&
                deniedTables.stream().anyMatch(dt -> dt.getTableName().equals(tableName));
    }

    @PreUpdate
    public void setDateUpdate() {
        this.dateUpdate = LocalDateTime.now();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }

        if (!(obj instanceof User)) {
            return false;
        }

        User otherUser = (User) obj;

        return this.getId().equals(otherUser.getId());

    }
}
