package auth.api.interfaces;

import auth.api.model.user.UserAuthenticationDTO;
import org.springframework.http.ResponseEntity;

public interface IUserService {
    ResponseEntity<?> registerUser(UserAuthenticationDTO registerDTO);
    ResponseEntity<?> login(UserAuthenticationDTO loginDTO);
}
