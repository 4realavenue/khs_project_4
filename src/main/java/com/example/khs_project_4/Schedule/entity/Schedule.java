package com.example.khs_project_4.Schedule.entity;

import com.example.khs_project_4.User.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "schedules")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Schedule extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @Column(length = 50, nullable = false)
    private String title;
    @Column(length = 100, nullable = false)
    private String content;


    public Schedule(User user, String title, String content) {
        this.user = user;
        this.title = title;
        this.content = content;

    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }


}
