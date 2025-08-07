package ee.tiiukuusik.lessonScheduler.persistence.timeslot;

import ee.tiiukuusik.lessonScheduler.controller.dto.TimeSlotDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface TimeSlotMapper {

TimeSlotDto toDto(TimeSlot timeSlot);

TimeSlot toEntity(TimeSlotDto timeSlotDto);

List<TimeSlotDto> toDtoList(List<TimeSlot> timeSlots);

List<TimeSlot> toEntityList(List<TimeSlotDto> timeSlotDtos);
}
