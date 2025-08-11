package ee.tiiukuusik.lessonScheduler.persistence.booking;

import ee.tiiukuusik.lessonScheduler.controller.booking.dto.BookingDto;
import ee.tiiukuusik.lessonScheduler.controller.booking.dto.BookingInfo;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface BookingMapper {



    @Mapping(source = "bookingDate", target = "bookingDate")
    @Mapping(source = "lessonType.typeName", target = "lessonType")
    //@Mapping(source = "status", target = "status")
    @Mapping(source = "timeSlot.startDatetime", target = "startDatetime")
    @Mapping(source = "customer.email", target = "customer")
    BookingDto toBookingDto(Booking booking);


    @InheritConfiguration(name="toBookingDto")
    @Mapping(source ="id", target = "bookingId")
    BookingInfo toBookingInfo(Booking booking);


    List<BookingInfo> toBookingInfos(List<Booking> bookings);



    //@Mapping(source = "status", target = "status")
    @Mapping(source = "bookingDate", target = "bookingDate")
    @Mapping(ignore = true, target = "timeSlot")
    @Mapping(ignore = true, target = "lessonType")
    @Mapping(ignore = true, target = "customer")
    Booking toBooking(BookingDto bookingDto);
}