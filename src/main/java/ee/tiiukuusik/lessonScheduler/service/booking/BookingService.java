package ee.tiiukuusik.lessonscheduler.service.booking;

import ee.tiiukuusik.lessonscheduler.controller.booking.dto.BookingDto;
import ee.tiiukuusik.lessonscheduler.controller.booking.dto.BookingInfo;
import ee.tiiukuusik.lessonscheduler.infrastructure.rest.error.Error;
import ee.tiiukuusik.lessonscheduler.infrastructure.rest.exception.DataNotFoundException;
import ee.tiiukuusik.lessonscheduler.infrastructure.rest.exception.ForbiddenException;
import ee.tiiukuusik.lessonscheduler.persistence.booking.Booking;
import ee.tiiukuusik.lessonscheduler.persistence.booking.BookingMapper;
import ee.tiiukuusik.lessonscheduler.persistence.booking.BookingRepository;
import ee.tiiukuusik.lessonscheduler.persistence.customer.Customer;
import ee.tiiukuusik.lessonscheduler.persistence.customer.CustomerRepository;
import ee.tiiukuusik.lessonscheduler.persistence.lessontype.LessonType;
import ee.tiiukuusik.lessonscheduler.persistence.lessontype.LessonTypeRepository;
import ee.tiiukuusik.lessonscheduler.persistence.timeslot.TimeSlot;
import ee.tiiukuusik.lessonscheduler.persistence.timeslot.TimeSlotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.Instant;
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
        TimeSlot timeSlot = getAvailableTimeSlot(bookingDto.getStartDatetime());
        LessonType lessonType = getValidLessonType(bookingDto.getLessonType());
        Customer customer = getValidCustomer(bookingDto.getCustomer());

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
        Booking booking = getValidBooking(id);
        return bookingMapper.toBookingDto(booking);
    }

    public List<BookingInfo> findAllBookings() {
        List<Booking> bookings = bookingRepository.findAll();
        return bookingMapper.toBookingInfos(bookings);
    }

    public void updateBooking(Integer id, BookingDto bookingDto) {
        Booking booking = getValidBooking(id);
        TimeSlot timeSlot = getAvailableTimeSlot(bookingDto.getStartDatetime());
        Customer customer = getValidCustomer(bookingDto.getCustomer());
        LessonType lessonType = getValidLessonType(bookingDto.getLessonType());
        bookingMapper.updateBooking(bookingDto, booking);
        booking.setTimeSlot(timeSlot);
        booking.setLessonType(lessonType);
        booking.setCustomer(customer);
        bookingRepository.save(booking);
    }

    public void deleteBooking(Integer id) {
        Booking booking = getValidBooking(id);
        freeTimeSlotAfterDelete(booking.getTimeSlot());
        bookingRepository.deleteById(id);
    }

    private Booking getValidBooking(Integer id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(Error.BOOKING_DOES_NOT_EXIST.getMessage()));
    }

    private TimeSlot getAvailableTimeSlot(Instant startDatetime) {
        TimeSlot timeSlot = timeSlotRepository.findByStartDatetime(startDatetime)
                .orElseThrow(() -> new DataNotFoundException(Error.START_TIME_DOES_NOT_EXIST.getMessage()));
        if (Boolean.FALSE.equals(timeSlot.getIsAvailable())) {
            throw new ForbiddenException("Requested Time slot is not available");
        }
        return timeSlot;
    }

    private LessonType getValidLessonType(String typeName) {
        return lessonTypeRepository.findLessonTypeBy(typeName)
                .orElseThrow(() -> new DataNotFoundException(Error.LESSON_TYPE_DOES_NOT_EXIST.getMessage()));
    }

    private Customer getValidCustomer(String email) {
        return customerRepository.findCustomerBy(email)
                .orElseThrow(() -> new DataNotFoundException(Error.CUSTOMER_DOES_NOT_EXIST.getMessage()));
    }

    private void freeTimeSlotAfterDelete(TimeSlot timeSlot) {
        timeSlot.setIsAvailable(true);
        timeSlotRepository.save(timeSlot);
    }
}