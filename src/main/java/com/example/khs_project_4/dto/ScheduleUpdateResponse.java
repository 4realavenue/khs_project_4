package com.example.khs_project_4.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class ScheduleUpdateResponse {

    private final Long id;
    private final String writer;
    private final String title;
    private final String content;
    private final LocalDateTime createdAd;
    private final LocalDateTime modifiedAt;

}
