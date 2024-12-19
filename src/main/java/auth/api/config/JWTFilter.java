package auth.api.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JWTFilter extends OncePerRequestFilter {
    private final JWTService jwtService;


    public JWTFilter(JWTService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if ("/api/sign-up".equals(request.getRequestURI())) {
            System.out.println("Ignorando autenticação para /api/sign-up");
            filterChain.doFilter(request, response);
            return;
        }


        String header = request.getHeader("Authorization");

        if (StringUtils.hasText(header) && header.startsWith("Bearer ")) {
            String token = header.substring(7);

            try {
                var claims = jwtService.parseToken(token);

                String username = claims.getSubject();

                String role = claims.get("role", String.class);

                var auth = new UsernamePasswordAuthenticationToken(username, null, List.of(() -> role));
                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(auth);
            } catch (Exception error) {
                System.out.println(error);
            }
        }

        filterChain.doFilter(request, response);
    }
}
