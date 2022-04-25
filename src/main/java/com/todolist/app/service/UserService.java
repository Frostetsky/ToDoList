package com.todolist.app.service;

import com.todolist.app.dto.SignUpDto;
import com.todolist.app.exception.AdminIdentificationException;
import com.todolist.app.model.Role;
import com.todolist.app.model.User;
import com.todolist.app.model.UserRole;
import com.todolist.app.repository.UserRepository;
import com.todolist.app.util.Constants;
import com.todolist.app.util.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final Environment environment;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, Environment environment, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.environment = environment;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username).
                orElseThrow(() -> new UsernameNotFoundException("Пользователь с логином " + username + "не найден."));

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getUserRoles()));
    }

    @Transactional
    public void save(User user) {
        userRepository.signUp(user);
    }


    public User identificationUser(SignUpDto signUpDto) throws AdminIdentificationException {

        User user = new User();
        user.setUsername(signUpDto.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(signUpDto.getPassword()));

        if (isAdmin(signUpDto)) {
            user.setUserRoles(Set.of(new UserRole(Role.ROLE_ADMIN)));
        } else {
            user.setUserRoles(Set.of(new UserRole(Role.ROLE_USER)));
        }
        return user;
    }

    private Collection< ? extends GrantedAuthority> mapRolesToAuthorities(Set<UserRole> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRole().name())).collect(Collectors.toList());
    }

    private boolean isAdmin(SignUpDto signUpDto) throws AdminIdentificationException {
        String clientId = environment.getProperty(Keys.ADMIN_CLIENT_ID);
        String clientSecret = environment.getProperty(Keys.ADMIN_CLIENT_SECRET);
        if (Objects.equals(signUpDto.getClientId(), clientId) && Objects.equals(signUpDto.getClientSecret(), clientSecret)) {
            return true;
        } else if (Objects.equals(signUpDto.getClientSecret(), Constants.NULL) || Objects.equals(signUpDto.getClientId(), Constants.NULL)) {
            return false;
        } else {
            throw new AdminIdentificationException("Неверные параметры client-id и/или client-secret для регистрации администратора.");
        }
    }
}
