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
    public ScheduleCreateResponse createResponse(ScheduleCreateRequest request, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
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
    public ScheduleUpdateResponse updateResponse(Long scheduleId, ScheduleUpdateRequest scheduleUpdateRequest, Long userId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalArgumentException("해당 일정은 비어 있습니다.")
        );

        if (!schedule.getUser().getUserId().equals(userId)) {
            throw new IllegalArgumentException("본인의 일정만 수정할 수 있습니다.");
        }

        schedule.update(
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
    public void deleteResponse(Long scheduleId, Long userId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalArgumentException("본인의 일정만 삭제할 수 있습니다.")
        );
        if (!schedule.getUser().getUserId().equals(userId)) {
            throw new IllegalArgumentException("본인의 일정만 삭제할 수 있습니다.");
        }
        scheduleRepository.delete(schedule);
    }

}
