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
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;
    private final TimeSlotRepository timeSlotRepository;
    private final LessonTypeRepository lessonTypeRepository;
    private final CustomerRepository customerRepository;

    @Transactional
    public void addBooking(BookingDto bookingDto) {
        TimeSlot timeSlot = getAvailableTimeSlot(bookingDto.getStartDatetime());
        LessonType lessonType = getValidLessonType(bookingDto.getLessonType());
        Customer customer = getValidCustomer(bookingDto.getCustomerEmail());
        Booking booking = bookingMapper.toBooking(bookingDto);
        booking.setTimeSlot(timeSlot);
        booking.setLessonType(lessonType);
        booking.setCustomer(customer);
        booking.setStatus("pending");
        booking.setBookingDate(LocalDateTime.now());
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

    @Transactional
    public void updateBooking(Integer id, BookingDto bookingDto) {
        Booking existingBooking = getValidBooking(id);
        Customer customer = getValidCustomer(bookingDto.getCustomerEmail());
        LessonType lessonType = getValidLessonType(bookingDto.getLessonType());
        TimeSlot updatedTimeSlot = handleTimeSlotUpdate(existingBooking, bookingDto.getStartDatetime());
        updateBookingDetails(existingBooking, bookingDto, customer, lessonType, updatedTimeSlot);
    }

    public void deleteBooking(Integer id) {
        Booking booking = getValidBooking(id);
        releaseExistingTimeSlot(booking.getTimeSlot());
        bookingRepository.deleteById(id);
    }

    public Booking getValidBooking(Integer id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(Error.BOOKING_DOES_NOT_EXIST.getMessage()));
    }

    private TimeSlot getAvailableTimeSlot(LocalDateTime startDatetime) {
        TimeSlot updatedTimeSlot = timeSlotRepository.findByStartDatetime(startDatetime)
                .orElseThrow(() -> new DataNotFoundException(Error.START_TIME_DOES_NOT_EXIST.getMessage()));
        if (Boolean.FALSE.equals(updatedTimeSlot.getIsAvailable())) {
            throw new ForbiddenException(Error.TIME_SLOT_IS_BOOKED.getMessage());
        }
        return updatedTimeSlot;
    }

    private LessonType getValidLessonType(String typeName) {
        return lessonTypeRepository.findLessonTypeBy(typeName)
                .orElseThrow(() -> new DataNotFoundException(Error.LESSON_TYPE_DOES_NOT_EXIST.getMessage()));
    }

    private Customer getValidCustomer(String email) {
        return customerRepository.findCustomerBy(email)
                .orElseThrow(() -> new DataNotFoundException(Error.CUSTOMER_DOES_NOT_EXIST.getMessage()));
    }

    private TimeSlot handleTimeSlotUpdate(Booking existingBooking, LocalDateTime newStartDateTime) {
        TimeSlot existingTimeSlot = existingBooking.getTimeSlot();
        if (isTimeSlotChangeRequested(existingTimeSlot, newStartDateTime)) {
            TimeSlot updatedTimeSlot = getAvailableTimeSlot(newStartDateTime);
            releaseExistingTimeSlot(existingTimeSlot);
            updatedTimeSlot.setIsAvailable(false);
            timeSlotRepository.save(updatedTimeSlot);
            return updatedTimeSlot;
        }
        return existingTimeSlot;
    }

    private boolean isTimeSlotChangeRequested(TimeSlot existingTimeSlot, LocalDateTime newStartDateTime) {
        return !existingTimeSlot.getStartDatetime().equals(newStartDateTime);
    }

    private void releaseExistingTimeSlot(TimeSlot timeSlot) {
        timeSlot.setIsAvailable(true);
        timeSlotRepository.save(timeSlot);
    }

    private void updateBookingDetails(Booking booking, BookingDto bookingDto,
                                      Customer customer, LessonType lessonType,
                                      TimeSlot timeSlot) {
        bookingMapper.updateBooking(bookingDto, booking);
        booking.setTimeSlot(timeSlot);
        booking.setLessonType(lessonType);
        booking.setCustomer(customer);
        bookingRepository.save(booking);
    }
}