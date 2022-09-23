package com.vuhien.application.security;

import com.vuhien.application.entity.User;
import com.vuhien.application.exception.NotFoundException;
import com.vuhien.application.repository.UserRepository;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(JwtUserDetailsService.class);

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(s);
        logger.debug("Authenticateing with user: " + user.getEmail());
        if (user != null) {
            logger.info("User Authenticated successful");
            return new CustomUserDetails(user);
        } else {
            logger.error("user does not exist");
            throw new UsernameNotFoundException("User get email " + s + " does not exist!");
        }

    }
}
