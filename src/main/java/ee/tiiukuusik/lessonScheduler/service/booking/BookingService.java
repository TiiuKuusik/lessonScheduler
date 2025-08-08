package ee.tiiukuusik.lessonScheduler.service.booking;

import ee.tiiukuusik.lessonScheduler.persistence.booking.Booking;
import ee.tiiukuusik.lessonScheduler.persistence.booking.BookingRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookingService {

    private BookingRepository bookingRepository;

    public void findBooking(Integer id) {
        Optional<Booking> optionalBooking = bookingRepository.findById(id);

    }
}
