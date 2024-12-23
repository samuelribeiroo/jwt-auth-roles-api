package auth.api.controller;


import auth.api.config.JWTService;
import auth.api.model.user.TokenDTO;
import auth.api.model.user.UserAuthenticationDTO;
import auth.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private  final JWTService jwtService;

    @Autowired
    public AuthController(UserService userService, AuthenticationManager authenticationManager, JWTService jwtService) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> registerUser(@RequestBody UserAuthenticationDTO registerUserDTO) {
        return userService.registerUser(registerUserDTO);
    }

}



