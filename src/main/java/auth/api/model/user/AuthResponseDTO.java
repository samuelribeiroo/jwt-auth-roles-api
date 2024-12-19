package auth.api.model.user;

public class AuthResponseDTO {
    private String token;

    public AuthResponseDTO(String token) {
        this.token = token;
    }

    // Getter
    public String getToken() {
        return token;
    }

    // Setters
    public void setToken(String token) {
        this.token = token;
    }
}
