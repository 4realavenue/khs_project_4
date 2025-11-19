package com.example.khs_project_4.User.controller;

import com.example.khs_project_4.User.dto.*;
import com.example.khs_project_4.User.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

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
    public void userDeleteResponse(@PathVariable Long userId) {
        userService.userDeleteResponse(userId);
    }


}
