package ee.tiiukuusik.lessonScheduler.controller.booking;

import ee.tiiukuusik.lessonScheduler.service.booking.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @GetMapping("/booking/{id}")
    public void findBooking(@PathVariable Integer id) {
        bookingService.findBooking(id);

    }
}
