package com.example.khs_project_4.User.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(length = 50, nullable = false)
    String userName;
    @Column(length = 50, unique = true, nullable = false)
    String userEmail;
    String userPassword;

    public User(String userName, String userEmail, String userPassword) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
    }

    public void updateUser(String userName, String userEmail) {
        this.userName = userName;
        this.userEmail = userEmail;
    }
}
