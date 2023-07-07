package com.lahee.security.service;

import com.lahee.security.entity.UserEntity;
import com.lahee.security.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Slf4j
@Service
public class JpaUseDetailManager implements UserDetailsManager {
    private final UserRepository userRepository;

    public JpaUseDetailManager(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        //테스트 유저 생성
        createUser(User.withUsername("qwer").password(passwordEncoder.encode("qwer")).build());
    }

    @Override
    // UserDetailsService.loadUserByUsername(String)
    // 실제로 Spring Security 내부에서 사용하는 반드시 구현해야 정상동작을 기대할 수 있는 메소드
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> optionalUser
                = userRepository.findByUsername(username);
        if (optionalUser.isEmpty())
            throw new UsernameNotFoundException(username);
        UserEntity userEntity = optionalUser.get();
        return User.withUsername(userEntity.getUsername())
                .password(userEntity.getPassword())
                .build();
    }

    @Override
    // 계정이름을 가진 사용자가 존재하는지 확인하는 메소드 (선택)
    public boolean userExists(String username) {
        return this.userRepository.existsByUsername(username);
    }

    @Override //새로운 사용자 저장
    public void createUser(UserDetails user) {
        if (this.userExists(user.getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(user.getUsername());
        userEntity.setPassword(user.getPassword());
        this.userRepository.save(userEntity);
    }

    @Override
    public void updateUser(UserDetails user) {

    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }
}
