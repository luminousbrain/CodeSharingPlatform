package ru.gontarenko.code.sharing.platform.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Role
 */
public enum AccountType {
    FREE(Set.of(Permission.READ_CREATE)),
    PRO(Set.of(Permission.READ_CREATE, Permission.PRIVATE_CREATE)),
    ADMIN(Set.of(Permission.READ_CREATE, Permission.PRIVATE_CREATE, Permission.ADMIN_PERMISSIONS));

    private final Set<Permission> permissions;

    AccountType(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getAuthorities() {
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}
