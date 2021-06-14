package ru.gontarenko.codesharingplatform.secutrity;

/**
 * Authority
 */
public enum Permission {
    READ_CREATE("free_user:read_create"),
    PRIVATE_CREATE("pro_user:private_create"),
    ADMIN_PERMISSIONS("admin:all_permissions");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }

    @Override
    public String toString() {
        return permission;
    }
}