package com.example.khs_project_4.Schedule.service;

import com.example.khs_project_4.Schedule.dto.*;
import com.example.khs_project_4.Schedule.entity.Schedule;
import com.example.khs_project_4.Schedule.repository.ScheduleRepository;
import com.example.khs_project_4.User.entity.User;
import com.example.khs_project_4.User.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    @Transactional
    public ScheduleCreateResponse createResponse(ScheduleCreateRequest request) {
        User user = userRepository.findById(request.getUserId()).orElseThrow(
                () -> new IllegalArgumentException("그 유저 아이디는 없는 아이디에요")
        );
        Schedule schedule = new Schedule(
                user,
                request.getTitle(),
                request.getContent()
        );
        Schedule saveSchedule = scheduleRepository.save(schedule);

        ScheduleCreateResponse response = new ScheduleCreateResponse(
                saveSchedule.getId(),
                saveSchedule.getTitle(),
                saveSchedule.getContent(),
                saveSchedule.getCreatedAt(),
                saveSchedule.getModifiedAt()
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
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }

    @Transactional(readOnly = true)
    public List<ScheduleGetOneResponse> getAllResponse() {
        List<Schedule> scheduleList = scheduleRepository.findAll();

        List<ScheduleGetOneResponse> dtos = new ArrayList<>();
        for (Schedule schedule : scheduleList) {
            ScheduleGetOneResponse dto = new ScheduleGetOneResponse(
                    schedule.getId(),
                    schedule.getTitle(),
                    schedule.getContent(),
                    schedule.getCreatedAt(),
                    schedule.getModifiedAt()
            );
            dtos.add(dto);
        }
        return dtos;
    }

    @Transactional
    public ScheduleUpdateResponse updateResponse(Long scheduleId, ScheduleUpdateRequest scheduleUpdateRequest) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalArgumentException("해당 일정은 비어 있습니다.")
        );

        schedule.update(
                scheduleUpdateRequest.getUserId(),
                scheduleUpdateRequest.getTitle(),
                scheduleUpdateRequest.getContent()
        );
        return new ScheduleUpdateResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
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
