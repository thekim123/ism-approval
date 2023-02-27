package com.hictc.ism.user.service;

import com.hictc.ism.user.dto.UserDto;
import com.hictc.ism.user.entity.User;
import com.hictc.ism.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public void createUser(UserDto dto) {
        User user = new User().dtoToEntity(dto)
                .encodingPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
        userRepository.save(user);
    }
}
