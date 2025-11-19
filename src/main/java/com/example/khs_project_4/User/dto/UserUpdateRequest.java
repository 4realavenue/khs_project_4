package com.example.khs_project_4.User.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserUpdateRequest {
    private String userName;
    private String userEmail;
    private String userPassword;


}
