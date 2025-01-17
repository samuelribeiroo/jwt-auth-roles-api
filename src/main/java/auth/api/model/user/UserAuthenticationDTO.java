package auth.api.model.user;

import jakarta.validation.constraints.*;

import java.util.*;

public class UserAuthenticationDTO {
    private String username;
    private String password;
    private Set<UserRoles> roles;

    // Getters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Set<UserRoles> getRoles() {
        return roles;
    }

    // Setters
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRoles(Set<UserRoles> roles) {
        this.roles = roles;
    }
}
