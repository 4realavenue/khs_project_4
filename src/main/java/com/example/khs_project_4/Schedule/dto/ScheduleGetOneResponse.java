package com.example.khs_project_4.Schedule.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class ScheduleGetOneResponse {

    private final Long id;
    private final String title;
    private final String content;
    private final LocalDateTime createdAd;
    private final LocalDateTime modifiedAt;

}
