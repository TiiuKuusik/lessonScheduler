package ee.tiiukuusik.lessonscheduler.service.timeslot;


import ee.tiiukuusik.lessonscheduler.controller.timeslot.dto.TimeSlotDto;
import ee.tiiukuusik.lessonscheduler.infrastructure.rest.error.Error;
import ee.tiiukuusik.lessonscheduler.infrastructure.rest.exception.DataNotFoundException;
import ee.tiiukuusik.lessonscheduler.infrastructure.rest.exception.ForbiddenException;
import ee.tiiukuusik.lessonscheduler.persistence.timeslot.TimeSlot;
import ee.tiiukuusik.lessonscheduler.persistence.timeslot.TimeSlotMapper;
import ee.tiiukuusik.lessonscheduler.persistence.timeslot.TimeSlotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class TimeSlotService {
    private final TimeSlotRepository timeSlotRepository;
    private final TimeSlotMapper timeSlotMapper;


    public void addTimeSlot(TimeSlotDto timeSlotDto) {
        TimeSlot timeSlot = timeSlotMapper.toTimeSlot(timeSlotDto);
        timeSlotRepository.save(timeSlot);
    }

    public List<TimeSlotDto> getAllTimeSlots() {
        List<TimeSlot> timeSlots = timeSlotRepository.findAll();
        return timeSlotMapper.toDtoList(timeSlots);
    }

    public void updateTimeSlot(Integer id, TimeSlotDto timeSlotDto) {
        TimeSlot existingTimeSlot = timeSlotRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(Error.TIME_SLOT_DOES_NOT_EXIST.getMessage()));
        TimeSlot updatedTimeSlot = timeSlotMapper.toTimeSlot(timeSlotDto);
        updatedTimeSlot.setId(existingTimeSlot.getId());
        timeSlotRepository.save(updatedTimeSlot);
    }

    public void deleteAvailableTimeSlot(Integer id) {
    TimeSlot timeSlot = timeSlotRepository.findById(id)
            .orElseThrow(() -> new DataNotFoundException(Error.TIME_SLOT_DOES_NOT_EXIST.getMessage()));
            
    if (Boolean.FALSE.equals(timeSlot.getIsAvailable())) {
        throw new ForbiddenException(Error.TIME_SLOT_IS_BOOKED.getMessage());
    }
    timeSlotRepository.deleteById(id);
    }
}