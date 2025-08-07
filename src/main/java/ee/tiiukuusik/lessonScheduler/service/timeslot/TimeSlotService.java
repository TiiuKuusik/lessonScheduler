package ee.tiiukuusik.lessonScheduler.service.timeslot;


import ee.tiiukuusik.lessonScheduler.controller.dto.TimeSlotDto;
import ee.tiiukuusik.lessonScheduler.persistence.timeslot.TimeSlot;
import ee.tiiukuusik.lessonScheduler.persistence.timeslot.TimeSlotMapper;
import ee.tiiukuusik.lessonScheduler.persistence.timeslot.TimeSlotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class TimeSlotService {
    private final TimeSlotRepository timeSlotRepository;
    private final TimeSlotMapper timeSlotMapper;


    public void addTimeSlot(TimeSlotDto timeSlotDto) {
        TimeSlot timeSlot = timeSlotMapper.toEntity(timeSlotDto);
        timeSlotRepository.save(timeSlot);
    }
}
