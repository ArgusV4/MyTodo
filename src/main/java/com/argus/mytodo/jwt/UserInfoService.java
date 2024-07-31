package com.argus.mytodo.jwt;

import com.argus.mytodo.entities.User;
import com.argus.mytodo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UserInfoService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> userDetail = repository.findByEmail(email);
        return userDetail.map(user -> new UserInfoDetails(user))
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
    }


}
