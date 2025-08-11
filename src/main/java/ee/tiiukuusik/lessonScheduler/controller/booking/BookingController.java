package ee.tiiukuusik.lessonScheduler.controller.booking;

import ee.tiiukuusik.lessonScheduler.controller.booking.dto.BookingDto;
import ee.tiiukuusik.lessonScheduler.controller.booking.dto.BookingInfo;
import ee.tiiukuusik.lessonScheduler.infrastructure.rest.error.ApiError;
import ee.tiiukuusik.lessonScheduler.service.booking.BookingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping("/booking")
    @Operation(summary = "Adds a booking", description = "Adds a booking. Throws an error if startDateTime, lessonType or customer is not found")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Invalid request body",
                    content = @Content(schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "404", description = "Foreign key field not found",
                    content = @Content(schema = @Schema(implementation = ApiError.class))),
    })
    public void addBooking(@RequestBody @Valid BookingDto bookingDto) {
        bookingService.addBooking(bookingDto);
    }



    @GetMapping("/booking/{id}")
    @Operation(summary = "Find a booking by id", description = "Finds a booking by id from the database. If not found, throws an error")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Booking found successfully"),
            @ApiResponse(responseCode = "404", description = "Booking not found",
            content = @Content(schema = @Schema(implementation = ApiError.class))),
    })
    public BookingDto findBooking(@PathVariable Integer id) {
        return bookingService.findBooking(id);
    }

    @GetMapping("booking/all")
    @Operation(summary = "Find all bookings", description = "Finds all bookings from the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bookings returned successfully")})
    public List<BookingInfo> findAllBookings() {
            return bookingService.findAllBookings();
            }
}

