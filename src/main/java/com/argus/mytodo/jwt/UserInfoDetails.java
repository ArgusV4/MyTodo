package com.argus.mytodo.jwt;
import com.argus.mytodo.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.*;
import java.util.stream.Collectors;
public class UserInfoDetails implements UserDetails {

    private String email;
    private String password;
    private List<GrantedAuthority> authorities;
    private static final String authoritySyntax = "ROLE_";

    public UserInfoDetails(User userInfo) {
        email = userInfo.getEmail();
        password = userInfo.getPassword();
        authorities = Arrays.stream(new String[]{this.authoritySyntax + userInfo.getRole().getValue()})
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
