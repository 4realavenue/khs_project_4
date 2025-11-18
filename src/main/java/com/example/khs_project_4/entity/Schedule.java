package com.example.khs_project_4.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@Getter
@Entity
@Table(name = "schedules")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50, nullable = false)
    private String writer;
    @Column(length = 50, nullable = false)
    private String title;
    @Column(length = 100, nullable = false)
    private String content;


    public Schedule(String writer, String title, String content) {
        this.writer = writer;
        this.title = title;
        this.content = content;
    }

    public void update(String writer, String title, String content) {
        this.writer = writer;
        this.title = title;
        this.content = content;
    }


}
