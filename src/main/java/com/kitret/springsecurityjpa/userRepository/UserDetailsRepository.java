package com.kitret.springsecurityjpa.userRepository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.kitret.springsecurityjpa.entities.User;


public interface UserDetailsRepository extends JpaRepository<User,Integer> {

    Optional<User> findByUserName(String userName);

}
