package com.hictc.ism.controller;

import com.hictc.ism.config.auth.PrincipalDetails;
import com.hictc.ism.dto.user.UserDto;
import com.hictc.ism.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserDto.Create dto) {
        userService.createUser(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("회원 등록 완료");
    }

    @GetMapping
    public ResponseEntity<?> findUser(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findUser(principalDetails));
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAllUser() {
        List<?> dtoList = userService.findAllUser();
        return ResponseEntity.status(HttpStatus.OK).body(dtoList);
    }

    @PutMapping
    public ResponseEntity<?> updateUser(@AuthenticationPrincipal PrincipalDetails principalDetails, @RequestBody UserDto.Create dto) {
        userService.updateUser(principalDetails, dto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("회원 수정 완료");
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUser(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        userService.deleteUser(principalDetails);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("회원 삭제 완료");
    }
}
