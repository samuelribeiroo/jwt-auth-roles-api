package auth.api.model.user;

public class AuthResponseDTO {
    private String token;
    private String username;
    private String password;

    public AuthResponseDTO(String username, String password, String token) {
        this.password = password;
        this.username = username;
        this.token = token;
    }

    // Getter
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getToken() {
        return token;
    }

    // Setters
    public void setToken(String token) {
        this.token = token;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
