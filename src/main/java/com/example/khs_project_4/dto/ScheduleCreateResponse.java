package com.example.khs_project_4.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class ScheduleCreateResponse {

    private final Long id;
    private final String writer;
    private final String title;
    private final String content;
    private final LocalDateTime createdAd;
    private final LocalDateTime modifiedAt;
}
