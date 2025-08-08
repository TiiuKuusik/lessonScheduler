package ee.tiiukuusik.lessonScheduler.persistence.booking;

import ee.tiiukuusik.lessonScheduler.controller.booking.dto.BookingDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface BookingMapper {



    //Booking toEntity(BookingDto bookingDto);

    @Mapping(source = "bookingDate", target = "bookingDate")
    @Mapping(source = "lessonType.typeName", target = "lessonType")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "timeSlot.startDatetime", target = "timeSlot")
    @Mapping(source = "user.email", target = "user")
    BookingDto toDto(Booking booking);

    //@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    //Booking partialUpdate(BookingDto bookingDto, @MappingTarget Booking booking);
}