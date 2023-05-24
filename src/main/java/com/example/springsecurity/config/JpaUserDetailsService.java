package com.example.springsecurity.config;

import com.example.springsecurity.model.User;
import com.example.springsecurity.model.UserDetail;
import com.example.springsecurity.repo.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JpaUserDetailsService implements UserDetailsService {
    private final UserRepo userRepo;

    public JpaUserDetailsService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user=userRepo.findByEmail(email).orElseThrow();
        UserDetail userDetail=new UserDetail();
        userDetail.setUser(user);
        return userDetail;
    }
}
