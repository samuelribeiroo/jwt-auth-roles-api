package auth.api.services;

import auth.api.config.JWTService;
import auth.api.interfaces.IUserService;
import auth.api.model.user.AuthResponseDTO;
import auth.api.model.user.TokenDTO;
import auth.api.model.user.UserAuthenticationDTO;
import auth.api.model.user.Users;
import auth.api.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import java.text.MessageFormat;


@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder,  JWTService jwtService) {
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }


    public ResponseEntity<?> registerUser(@Valid @RequestBody UserAuthenticationDTO registerDTO) {
        if (registerDTO.getPassword() == null || registerDTO.getPassword().isBlank()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O campo 'password' é obrigatório.");
        }

        if (userRepository.findByUsername(registerDTO.getUsername()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Usuário já cadastrado");
        }

        // Criar Usuário
        Users user = new Users();

        user.setUsername(registerDTO.getUsername());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        user.setRoles(registerDTO.getRoles());

        // Salvar usuário
        userRepository.save(user);

        // Gerar Token
        String token = jwtService.generateToken(user.getUsername(), user.getRoles());

        ResponseEntity.ok(MessageFormat.format("Usuário criado com sucesso {0}", user.getUsername()));
        return ResponseEntity.status(HttpStatus.CREATED).body(new TokenDTO(token));
    }

    public ResponseEntity<?> login(@RequestBody UserAuthenticationDTO loginDTO) {
        var userExists = userRepository.findByUsername(loginDTO.getUsername());

        if (userExists.isEmpty()) {
          return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário não encontrado");
        }

        Users user = userExists.get();

        if (!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
          return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário e senha não autorizados.");
        }

        String token = jwtService.generateToken(user.getUsername(), user.getRoles());

        return ResponseEntity.status(HttpStatus.OK).body(new TokenDTO(token));
    }

}

