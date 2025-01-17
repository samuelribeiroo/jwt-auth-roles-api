package auth.api.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JWTFilter extends OncePerRequestFilter {
    private static final String BEARER_PREFIX = "Bearer ";
    private static final String ROLE_PREFIX = "ROLE_";

    private final JWTService jwtService;

    public JWTFilter(JWTService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();
        return path.equals("/api/login") || path.equals("/api/sign-up") || path.equals("/error");
    }

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");

        if (StringUtils.hasText(header) && header.startsWith(BEARER_PREFIX)) {
            String token = header.substring(BEARER_PREFIX.length());

            try {
                var claims = jwtService.parseToken(token);
                String roles = claims.get("roles", String.class);

                if (roles != null) {
                    String role = roles.replaceAll("[\\[\\]]", "");
                    var auth = createAuthentication(claims.getSubject(), role, request);
                    SecurityContextHolder.getContext().setAuthentication(auth);
                } else {
                    System.out.println("No roles found in the token!");
                }
            } catch (Exception error) {
                System.out.println(error.getMessage());
            }
        }
    }

    private UsernamePasswordAuthenticationToken createAuthentication(String subject, String role, HttpServletRequest request) {
        var authorities = List.of(new SimpleGrantedAuthority(ROLE_PREFIX + role));
        var auth = new UsernamePasswordAuthenticationToken(subject, null, authorities);
        auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        return auth;
    }
}