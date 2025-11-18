package com.example.khs_project_4.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ScheduleCreateRequest {

    private String writer;
    private String title;
    private String content;

}
