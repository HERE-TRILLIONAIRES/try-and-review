package com.trillionares.tryit.auth.infrastructure.config.jwt.filter;


import com.trillionares.tryit.auth.infrastructure.config.CustomUserDetails;
import com.trillionares.tryit.auth.infrastructure.config.CustomUserDetailsService;
import com.trillionares.tryit.auth.infrastructure.config.jwt.JwtUtil;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final CustomUserDetailsService customUserDetailsService;
    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
        FilterChain filterChain) throws ServletException, IOException {

        String token = request.getHeader("Authorization");
        if (token == null || !token.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        token = token.replace("Bearer ", "");
        try {
            // JWT에서 username과 role 추출
            String username = jwtUtil.getUsernameFromToken(token);
            String role = jwtUtil.getRoleFromToken(token);

            log.info("username {}, role {}", username, role);

            if (username != null && role != null) {
                CustomUserDetails userDetails = (CustomUserDetails) customUserDetailsService.loadUserByUsername(
                    username);

                if (!role.equals(userDetails.getRole())) {
                    log.warn("Role 불일치: 기대한 role은 {}, 그러나 실제 role은 {}", userDetails.getRole(),
                        role);
                    response.setContentType("application/json; charset=UTF-8");
                    response.setCharacterEncoding("UTF-8");
                    response.setStatus(HttpStatus.FORBIDDEN.value());
                    response.getWriter().write(
                        "{\"code\": -1, \"message\": \"Role 정보가 일치하지 않습니다\", \"data\": null}");
                    return;
                }

                // SecurityContextHolder에 인증 정보 설정
                UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(userDetails, null,
                        userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (JwtException e) {
            log.error("JWT parsing failed: {}", e.getMessage());
            response.setContentType("application/json; charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter()
                .write("{\"code\": -1, \"message\": \"유효하지 않은 토큰입니다\", \"data\": null}");
            return;
        }
        filterChain.doFilter(request, response);
    }
}
