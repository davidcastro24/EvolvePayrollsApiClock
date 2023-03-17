package com.davcode.clock.services;

import com.davcode.clock.models.User;
import com.davcode.clock.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);
        if (user == null)
            throw new UsernameNotFoundException("User " + username + " not found");
        return new org.springframework.security.core.userdetails.User(
                user.getUserName(),
                user.getPassword(),
                user.isActive(),
                true,
                true,
                user.isCredentialsExpired(),
                getAuthorities(user.getRoles())
        );
    }

    private static Collection<? extends GrantedAuthority> getAuthorities(List<String> roles) {
        //return Arrays.stream(Roles.values()).map(roles -> new SimpleGrantedAuthority(roles.getDescription())).collect(Collectors.toList())
        return roles.stream().map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toList());
    }

}
