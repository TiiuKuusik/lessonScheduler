package ee.tiiukuusik.lessonscheduler.persistence.timeslot;

import ee.tiiukuusik.lessonscheduler.controller.timeslot.dto.TimeSlotDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface TimeSlotMapper {

TimeSlotDto toTimeSlotDto(TimeSlot timeSlot);

TimeSlot toTimeSlot(TimeSlotDto timeSlotDto);

List<TimeSlotDto> toDtoList(List<TimeSlot> timeSlots);

List<TimeSlot> toTimeSlotList(List<TimeSlotDto> timeSlotDtos);
}
