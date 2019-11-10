package com.heilash.logistoffer.config.security;

import com.heilash.logistoffer.persistence.model.User;
import com.heilash.logistoffer.persistence.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> optional = userRepository.findByUsername(s);
        return optional.map(user -> new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>()))
                .orElseThrow(() -> new UsernameNotFoundException(s));
    }
}
