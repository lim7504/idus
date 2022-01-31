package com.example.idus.config.security.auth;

import com.example.idus.config.Code;
import com.example.idus.config.IdusException;
import com.example.idus.domain.User;
import com.example.idus.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = this.getUser(email);
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .authorities("ROLE_USER")
                .build();
    }

    private User getUser(String email) {
        return this.userRepository.findByEmail(email)
                .orElseThrow(() -> new IdusException(Code.EMAIL_NOT_EXIST));
    }
}
