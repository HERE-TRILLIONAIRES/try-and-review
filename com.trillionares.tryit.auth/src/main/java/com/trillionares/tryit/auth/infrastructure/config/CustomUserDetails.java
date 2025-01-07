package com.trillionares.tryit.auth.infrastructure.config;


import com.trillionares.tryit.auth.domain.model.Role;
import com.trillionares.tryit.auth.domain.model.User;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {

    private final User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Role role = user.getRole();

        // 단순 열거형을 권한 문자열로 변환
        String authority = "ROLE_" + role.name(); // "ROLE_ADMIN", "ROLE_COMPANY", "ROLE_MEMBER"

        // 권한 생성
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(authority);
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(simpleGrantedAuthority);

        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    public UUID getUserId() {
        return user.getUserId();
    }

    public Role getRole() { return user.getRole();}
}
