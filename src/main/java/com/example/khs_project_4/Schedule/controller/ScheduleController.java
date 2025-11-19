package com.example.khs_project_4.Schedule.controller;

import com.example.khs_project_4.Schedule.dto.*;
import com.example.khs_project_4.Schedule.service.ScheduleService;
import com.example.khs_project_4.User.dto.UserLoginResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping("/schedules")
    public ScheduleCreateResponse createSchedule(@RequestBody ScheduleCreateRequest scheduleCreateRequest, HttpSession httpSession) {

        UserLoginResponse loginUser = (UserLoginResponse) httpSession.getAttribute("loginUser");
        if (loginUser == null) throw new IllegalArgumentException("로그인이 필요합니다");

        return scheduleService.createResponse(
                scheduleCreateRequest,
                loginUser.getUserId());
    }

    @GetMapping("/schedules/{scheduleId}")
    public ScheduleGetOneResponse getOneSchedule(@PathVariable Long scheduleId) {
        return scheduleService.getOneResponse(scheduleId);
    }

    @GetMapping("/schedules")
    public List<ScheduleGetOneResponse> getAllSchedule(HttpSession httpSession) {
        UserLoginResponse loginUser = (UserLoginResponse) httpSession.getAttribute("loginUser");
        if (loginUser == null) {
            throw new IllegalArgumentException("로그인이 필요합니다.");
        }

        return scheduleService.getAllResponse();
    }

    @PutMapping("/schedules/{scheduleId}")
    public ScheduleUpdateResponse updateSchedule(
            @PathVariable Long scheduleId,
            @RequestBody ScheduleUpdateRequest scheduleUpdateRequest, HttpSession httpSession
            ) {

        UserLoginResponse loginUser = (UserLoginResponse) httpSession.getAttribute("loginUser");
        if (loginUser == null) {
            throw new IllegalArgumentException("로그인이 필요합니다.");
        }
        return scheduleService.updateResponse (
                scheduleId,
                scheduleUpdateRequest,
                loginUser.getUserId()
        );
    }

    @DeleteMapping("/schedules/{scheduleId}")
    public void deleteScheudle (@PathVariable Long scheduleId, HttpSession httpSession) {
        UserLoginResponse loginUser = (UserLoginResponse) httpSession.getAttribute("loginUser");
        if (loginUser == null) {
            throw new IllegalArgumentException("로그인이 필요합니다.");
        }
        scheduleService.deleteResponse(
                scheduleId,
                loginUser.getUserId()
        );
    }

}
