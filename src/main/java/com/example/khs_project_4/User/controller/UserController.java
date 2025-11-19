package com.example.khs_project_4.User.controller;

import com.example.khs_project_4.User.dto.*;
import com.example.khs_project_4.User.entity.User;
import com.example.khs_project_4.User.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/users/login")
    public ResponseEntity<String> userLoginResponse(@RequestBody UserLoginRequest userLoginRequest, HttpSession userLoginSession) {
        UserLoginResponse response = userService.userLoginResponse(userLoginRequest);
        userLoginSession.setAttribute("loginUser", response);

        return ResponseEntity.ok("로그인 되었습니다.");
    }

    @PostMapping("/users/logout")
    public ResponseEntity<String> userLogoutResponse(HttpSession httpSession) {
        httpSession.invalidate();
        return ResponseEntity.ok("로그아웃 되었습니다.");
    }

    @PostMapping("/users")
    public UserCreateResponse userCreateResponse(@RequestBody UserCreateRequest userCreateRequest) {
        return userService.userCreateResponse(userCreateRequest);
    }

    @GetMapping("/users/{userId}")
    public UserGetOneResponse userGetOneResponse(@PathVariable Long userId) {
        return userService.userGetOneResponse(userId);
    }

    @GetMapping("/users")
    public List<UserGetOneResponse> userGetAllResponse() {
        return userService.userGetAllResponse();
    }

    @PutMapping("/users/{userId}")
    public UserUpdateResponse userUpdateResponse(
            @PathVariable Long userId,
            @RequestBody UserUpdateRequest userUpdateRequest) {
        return userService.userUpdateResponse(userId, userUpdateRequest);
    }

    @DeleteMapping("/users/{userId}")
    public void userDeleteResponse(@PathVariable Long userId, HttpSession httpSession) {
        userService.userDeleteResponse(userId);
    }

}
