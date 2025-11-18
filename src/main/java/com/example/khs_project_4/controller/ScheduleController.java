package com.example.khs_project_4.controller;

import com.example.khs_project_4.dto.*;
import com.example.khs_project_4.service.ScheduleService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping("/schedule")
    public ScheduleCreateResponse createSchedule(@RequestBody ScheduleCreateRequest scheduleCreateRequest) {
        return scheduleService.createResponse(scheduleCreateRequest);
    }

    @GetMapping("/schedule/{scheduleId}")
    public ScheduleGetOneResponse getOneSchedule(@PathVariable Long scheduleId) {
        return scheduleService.getOneResponse(scheduleId);
    }

    @GetMapping("/schedules")
    public List<ScheduleGetOneResponse> getAllSchedule() {
        return scheduleService.getAllResponse();
    }

    @PutMapping("/schedule/{scheduleId}")
    public ScheduleUpdateResponse updateSchedule(
            @PathVariable Long scheduleId,
            @RequestBody ScheduleUpdateRequest scheduleUpdateRequest
            ) {
        return scheduleService.updateResponse (scheduleId, scheduleUpdateRequest);
    }

    @DeleteMapping("/schedule/{scheduleId}")
    public void deleteScheudle (@PathVariable Long scheduleId) {
        scheduleService.deleteResponse(scheduleId);
    }
}
