package com.example.khs_project_4.User.service;

import com.example.khs_project_4.User.dto.*;

import com.example.khs_project_4.User.entity.User;
import com.example.khs_project_4.User.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserLoginResponse userLoginResponse(UserLoginRequest userLoginRequest) {
        User user = userRepository.findByUserEmail(userLoginRequest.getUserEmail()).orElseThrow(
                () -> new IllegalArgumentException("이메일이 잘 못 되었거나, 사용자가 등록되어 있지 않습니다")
        );
        if (!user.getUserPassword().equals(userLoginRequest.getUserPassword())) {
            throw new IllegalArgumentException("비밀번호가 잘 못 되었거나, 사용자가 등록되어 있지 않습니다.");
        }
        return new UserLoginResponse(user.getUserId(),
                user.getUserName(),
                user.getUserEmail()
        );
    }

    @Transactional
    public UserCreateResponse userCreateResponse(UserCreateRequest userCreateRequest) {
        User user = new User (
                userCreateRequest.getUserName(),
                userCreateRequest.getUserEmail(),
                userCreateRequest.getUserPassword()
        );
        User savedUser = userRepository.save(user);

        UserCreateResponse response = new UserCreateResponse(
                savedUser.getUserId(),
                savedUser.getUserName(),
                savedUser.getUserEmail()
        );
        return response;
    }

    @Transactional
    public UserGetOneResponse userGetOneResponse(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("해당 유저는 존재하지 않습니다.")
        );
        return new UserGetOneResponse(
                user.getUserId(),
                user.getUserName(),
                user.getUserEmail()
        );
    }

    @Transactional
    public List<UserGetOneResponse> userGetAllResponse() {
        List<User> userList = userRepository.findAll();

        List<UserGetOneResponse> dtos = new ArrayList<>();
        for (User user : userList) {
            UserGetOneResponse dto = new UserGetOneResponse(
                    user.getUserId(),
                    user.getUserName(),
                    user.getUserEmail()
            );
            dtos.add(dto);
        }
        return dtos;
    }

    @Transactional
    public UserUpdateResponse userUpdateResponse(Long userId, UserUpdateRequest userUpdateRequest) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("해당 유저는 존재하지 않습니다.")
        );

        user.updateUser(
                userUpdateRequest.getUserName(),
                userUpdateRequest.getUserEmail()
        );
        return new UserUpdateResponse(
                user.getUserId(),
                user.getUserName(),
                user.getUserEmail()
        );
    }

    @Transactional
    public void userDeleteResponse(Long userId) {
        boolean userExistence = userRepository.existsById(userId);
        if (!userExistence) {
            throw new IllegalArgumentException("해당 유저는 존재하지 않습니다.");
        }
        userRepository.deleteById(userId);
    }

}
