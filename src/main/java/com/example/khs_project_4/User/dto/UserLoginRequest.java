package com.example.khs_project_4.User.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@NoArgsConstructor
public class UserLoginRequest {
    private String userEmail;
    private String userPassword;
}
