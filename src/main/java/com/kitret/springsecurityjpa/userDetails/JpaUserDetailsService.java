package com.kitret.springsecurityjpa.userDetails;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kitret.springsecurityjpa.entities.MyUserDetails;
import com.kitret.springsecurityjpa.entities.User;
import com.kitret.springsecurityjpa.userRepository.UserDetailsRepository;

// Uncomment below when using JpaUserDetailsService
// @Service
public class JpaUserDetailsService implements UserDetailsService{

    @Autowired
    UserDetailsRepository userDetailsRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userDetailsRepository.findByUserName(username);
        user.orElseThrow(() -> new UsernameNotFoundException("Not Found : "+ username));
        return user.map(MyUserDetails::new).get();
    }
    
}
