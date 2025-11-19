package com.example.khs_project_4.User.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserGetOneResponse {
    private final Long userId;
    private final String userName;
    private final String userEmail;

}
