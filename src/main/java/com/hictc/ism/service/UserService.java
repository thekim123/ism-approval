package com.hictc.ism.service;

import com.hictc.ism.config.auth.PrincipalDetails;
import com.hictc.ism.handler.exception.CustomApiException;
import com.hictc.ism.dto.user.UserDto;
import com.hictc.ism.entity.user.User;
import com.hictc.ism.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public void createUser(UserDto.Create dto) {
        User user = new User().dtoToEntity(dto)
                .encodingPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
        userRepository.save(user);
    }

    @Transactional
    public void updateUser(PrincipalDetails principalDetails, UserDto.Create dto) {
        User userEntity = findUserEntityByPrincipalDetails(principalDetails);
        userEntity.dtoToEntity(dto);
    }

    @Transactional
    public void deleteUser(PrincipalDetails principalDetails) {
        User userEntity = findUserEntityByPrincipalDetails(principalDetails);
        userRepository.delete(userEntity);
    }

    @Transactional(readOnly = true)
    public UserDto.Get findUser(PrincipalDetails principalDetails) {
        User user = findUserEntityByPrincipalDetails(principalDetails);
        return new UserDto.Get().entityToDto(user);
    }

    @Transactional(readOnly = true)
    public List<?> findAllUser() {
        List<User> users = userRepository.findAll();

        List<UserDto.Get> result = new ArrayList<>();
        users.forEach(u -> result.add(new UserDto.Get().entityToDto(u)));
        return result;
    }

    public User findUserEntityByPrincipalDetails(PrincipalDetails principalDetails) {
        Long userId = principalDetails.getUser().getId();
        return userRepository.findById(userId).orElseThrow(() -> {
            throw new CustomApiException("회원을 찾을 수 없습니다.");
        });
    }

}
