package com.example.khs_project_4.service;

import com.example.khs_project_4.dto.*;
import com.example.khs_project_4.entity.Schedule;
import com.example.khs_project_4.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Transactional
    public ScheduleCreateResponse createResponse(ScheduleCreateRequest request) {
        Schedule schedule = new Schedule(
                request.getWriter(),
                request.getTitle(),
                request.getContent()
        );
        Schedule saveSchedule = scheduleRepository.save(schedule);

        ScheduleCreateResponse response = new ScheduleCreateResponse(
                saveSchedule.getId(),
                saveSchedule.getWriter(),
                saveSchedule.getTitle(),
                saveSchedule.getContent()
        );
        return response;
    }

    @Transactional(readOnly = true)
    public ScheduleGetOneResponse getOneResponse(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalArgumentException("해당 일정은 비어 있습니다.")
        );
        return new ScheduleGetOneResponse(
                schedule.getId(),
                schedule.getWriter(),
                schedule.getTitle(),
                schedule.getContent()
        );
    }

    @Transactional(readOnly = true)
    public List<ScheduleGetOneResponse> getAllResponse() {
        List<Schedule> scheduleList = scheduleRepository.findAll();

        List<ScheduleGetOneResponse> dtos = new ArrayList<>();
        for (Schedule schedule : scheduleList) {
            ScheduleGetOneResponse dto = new ScheduleGetOneResponse(
                    schedule.getId(), schedule.getWriter(), schedule.getTitle(), schedule.getContent()
            );
            dtos.add(dto);
        }
        return dtos;
    }

    @Transactional
    public ScheduleUpdateResponse updateResponse(Long scheduleId, ScheduleUpdateRequest scheduleUpdateResponse) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalArgumentException("해당 일정은 비어 있습니다.")
        );

        schedule.update(
                scheduleUpdateResponse.getWriter(),
                scheduleUpdateResponse.getTitle(),
                scheduleUpdateResponse.getContent()
        );
        return new ScheduleUpdateResponse(
                schedule.getId(),
                schedule.getWriter(),
                schedule.getTitle(),
                schedule.getContent()
        );
    }

    @Transactional
    public void deleteResponse(Long scheduleId) {
        boolean scheduleExistence = scheduleRepository.existsById(scheduleId);
        if (!scheduleExistence) {
            throw new IllegalArgumentException("해당 일정은 비어 있습니다.");
        }
        scheduleRepository.deleteById(scheduleId);
    }

}
