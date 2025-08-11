package ee.tiiukuusik.lessonScheduler.service.booking;

import ee.tiiukuusik.lessonScheduler.controller.booking.dto.BookingDto;
import ee.tiiukuusik.lessonScheduler.controller.booking.dto.BookingInfo;
import ee.tiiukuusik.lessonScheduler.infrastructure.rest.error.Error;
import ee.tiiukuusik.lessonScheduler.infrastructure.rest.exception.DataNotFoundException;
import ee.tiiukuusik.lessonScheduler.infrastructure.rest.exception.ForbiddenException;
import ee.tiiukuusik.lessonScheduler.persistence.booking.Booking;
import ee.tiiukuusik.lessonScheduler.persistence.booking.BookingMapper;
import ee.tiiukuusik.lessonScheduler.persistence.booking.BookingRepository;
import ee.tiiukuusik.lessonScheduler.persistence.customer.Customer;
import ee.tiiukuusik.lessonScheduler.persistence.customer.CustomerRepository;
import ee.tiiukuusik.lessonScheduler.persistence.lessontype.LessonType;
import ee.tiiukuusik.lessonScheduler.persistence.lessontype.LessonTypeRepository;
import ee.tiiukuusik.lessonScheduler.persistence.timeslot.TimeSlot;
import ee.tiiukuusik.lessonScheduler.persistence.timeslot.TimeSlotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;
    private final TimeSlotRepository timeSlotRepository;
    private final LessonTypeRepository lessonTypeRepository;
    private final CustomerRepository customerRepository;


    public void addBooking(BookingDto bookingDto) {
       TimeSlot timeSlot = timeSlotRepository.findByStartDatetime(bookingDto.getStartDatetime())
                .orElseThrow(() -> new DataNotFoundException(Error.START_TIME_DOES_NOT_EXIST.getMessage()));

        if (Boolean.FALSE.equals(timeSlot.getIsAvailable())) {
            throw new ForbiddenException("Requested Time slot is not available");
        }

        LessonType lessonType = lessonTypeRepository.findLessonTypeBy(bookingDto.getLessonType())
                .orElseThrow(() -> new DataNotFoundException(Error.LESSON_TYPE_DOES_NOT_EXIST.getMessage()));
       Customer customer = customerRepository.findByEmail(bookingDto.getCustomer())
               .orElseThrow(() -> new DataNotFoundException(Error.CUSTOMER_DOES_NOT_EXIST.getMessage()));

        Booking booking = bookingMapper.toBooking(bookingDto);
        booking.setTimeSlot(timeSlot);
        booking.setLessonType(lessonType);
        booking.setCustomer(customer);
        booking.setStatus("pending");

        // Set the time slot as not available
        timeSlot.setIsAvailable(false);
        timeSlotRepository.save(timeSlot);
        bookingRepository.save(booking);
    }


    public BookingDto findBooking(Integer id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(Error.BOOKING_DOES_NOT_EXIST.getMessage()));
        return bookingMapper.toBookingDto(booking);
    }

    public List<BookingInfo> findAllBookings() {
        List<Booking> bookings = bookingRepository.findAll();
        return bookingMapper.toBookingInfos(bookings);
    }

}
