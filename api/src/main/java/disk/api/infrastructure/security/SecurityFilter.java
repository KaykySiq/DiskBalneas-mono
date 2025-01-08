package disk.api.infrastructure.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
                var token = this.recoverToken(request);
                if (token != null) {
                    var login = tokenService.validateToken(token);
                    var usernamePassword = new UsernamePasswordAuthenticationToken(login, null, null);
                    SecurityContextHolder.getContext().setAuthentication(usernamePassword);
                }
                filterChain.doFilter(request, response);
            }

            private String recoverToken(HttpServletRequest request) {
                var authHeader = request.getHeader("Authorization");
                if (authHeader == null) return null;
                return authHeader.replace("Bearer ", "");
            }
}