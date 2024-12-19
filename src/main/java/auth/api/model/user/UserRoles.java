package auth.api.model.user;

public enum UserRoles {
    ADMIN("ADMIN"),
    USER("USER" );

    public String roles;

    UserRoles(String role) { this.roles = role; }
}
