package ee.tiiukuusik.lessonScheduler.service.booking;

import ee.tiiukuusik.lessonScheduler.controller.booking.dto.BookingDto;
import ee.tiiukuusik.lessonScheduler.infrastructure.rest.error.Error;
import ee.tiiukuusik.lessonScheduler.infrastructure.rest.exception.DataNotFoundException;
import ee.tiiukuusik.lessonScheduler.persistence.booking.Booking;
import ee.tiiukuusik.lessonScheduler.persistence.booking.BookingMapper;
import ee.tiiukuusik.lessonScheduler.persistence.booking.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;

    public BookingDto findBooking(Integer id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(Error.BOOKING_DOES_NOT_EXIST.getMessage()));
        return bookingMapper.toDto(booking);
    }
}
