package ee.tiiukuusik.lessonScheduler.service.timeslot;


import ee.tiiukuusik.lessonScheduler.controller.timeslot.dto.TimeSlotDto;
import ee.tiiukuusik.lessonScheduler.persistence.timeslot.TimeSlot;
import ee.tiiukuusik.lessonScheduler.persistence.timeslot.TimeSlotMapper;
import ee.tiiukuusik.lessonScheduler.persistence.timeslot.TimeSlotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class TimeSlotService {
    private final TimeSlotRepository timeSlotRepository;
    private final TimeSlotMapper timeSlotMapper;


    public void addTimeSlot(TimeSlotDto timeSlotDto) {
        TimeSlot timeSlot = timeSlotMapper.toEntity(timeSlotDto);
        timeSlotRepository.save(timeSlot);
    }

    public List<TimeSlotDto> getAllTimeSlots() {
        List<TimeSlot> timeSlots = timeSlotRepository.findAll();
        return timeSlotMapper.toDtoList(timeSlots);
    }
}
