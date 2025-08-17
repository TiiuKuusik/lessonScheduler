package ee.tiiukuusik.lessonscheduler.persistence.booking;

import ee.tiiukuusik.lessonscheduler.controller.booking.dto.BookingDto;
import ee.tiiukuusik.lessonscheduler.controller.booking.dto.BookingInfo;
import org.mapstruct.*;
import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface BookingMapper {


    @Mapping(source = "lessonType.typeName", target = "lessonType")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "timeSlot.startDatetime", target = "startDatetime")
    @Mapping(source = "customer.email", target = "customerEmail")
    BookingDto toBookingDto(Booking booking);

    @InheritConfiguration(name="toBookingDto")
    @Mapping(source ="id", target = "bookingId")
    @Mapping(source ="bookingDate", target = "bookingDate")
    BookingInfo toBookingInfo(Booking booking);

    List<BookingInfo> toBookingInfos(List<Booking> bookings);


    @Mapping(ignore = true, target = "timeSlot")
    @Mapping(ignore = true, target = "lessonType")
    @Mapping(ignore = true, target = "customer")
    Booking toBooking(BookingDto bookingDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @InheritConfiguration(name="toBooking")
    @Mapping(source = "status", target = "status")
    void updateBooking(BookingDto bookingDto, @MappingTarget Booking booking);
}
