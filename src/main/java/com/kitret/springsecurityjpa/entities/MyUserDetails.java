package com.kitret.springsecurityjpa.entities;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MyUserDetails implements UserDetails {

    private String userName;
    private String password;
    private boolean active;
    
    private List<GrantedAuthority> auth;
    
    MyUserDetails(){
        
    }
    
    public MyUserDetails(String userName){
        this.userName=userName;
        this.password="pass";
        this.active=true;
        this.auth = Arrays.asList(new SimpleGrantedAuthority("USER"));
    }

    public MyUserDetails(User user){
        this.userName=user.getUserName();
        this.password=user.getPassword();
        this.active=user.isActive();
        this.auth=Arrays.stream(user.getRoles().split(","))
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
    }
    
    // MyUserDetails(String userName, String password,ArrayList<String> auth){
        //     this.userName=userName;
        //     this.password=password;
        //     auth.stream().forEach(s -> this.auth.add(new SimpleGrantedAuthority("ROLE_"+s.toUpperCase())));
        // }
        
    public boolean isActive() {
        return active;
    }
    
    public void setActive(boolean active) {
        this.active = active;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.auth;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userName;
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
        return active;
    }
    
}
