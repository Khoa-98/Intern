package com.vuhien.application.security;

import com.vuhien.application.entity.User;
import com.vuhien.application.exception.NotFoundException;
import com.vuhien.application.repository.UserRepository;
import com.vuhien.application.utils.LoggerUtil;

import lombok.extern.log4j.Log4j2;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    // private static final Logger logger =
    // LoggerFactory.getLogger(JwtUserDetailsService.class);

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(s);
        LoggerUtil.loggerLogin.debug("Authenticateing with user: " + user.getEmail());
        if (user != null) {
            LoggerUtil.loggerLogin.info("User Authenticated successful");
            return new CustomUserDetails(user);
        } else {
            LoggerUtil.loggerLogin.error("user " + s + "does not exist");
            throw new UsernameNotFoundException("User get email " + s + " does not exist!");
        }

    }
}
