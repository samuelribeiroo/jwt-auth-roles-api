package auth.api.config;

import auth.api.model.user.UserRoles;
import io.jsonwebtoken.Claims;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class JWTService {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    private Key key;

    @PostConstruct
    public void init() {
        key = Keys.hmacShaKeyFor(secret.trim().getBytes(StandardCharsets.UTF_8));

    }

    public String generateToken(String username, Set<UserRoles> role) {
        Instant now = Instant.now();

        Instant expireAt = now.plus(Duration.ofDays(expiration));

        return buildJwt(username, role, expireAt).compact();
    }

    public Claims parseToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public JwtBuilder buildJwt(String username, Set<UserRoles> role, Instant expireAt) {
        return Jwts.builder()
                .setSubject(username)
                .claim("role", role.stream().map(UserRoles::name).collect(Collectors.joining(",")))
                .setIssuedAt(new Date())
                .setExpiration(Date.from(expireAt))
                .signWith(key);
    }
}
