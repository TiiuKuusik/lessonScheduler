package ee.tiiukuusik.lessonscheduler.controller.timeslot;

import ee.tiiukuusik.lessonscheduler.controller.timeslot.dto.TimeSlotDto;
import ee.tiiukuusik.lessonscheduler.infrastructure.rest.error.ApiError;
import ee.tiiukuusik.lessonscheduler.service.timeslot.TimeSlotService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TimeSlotController {

    private final TimeSlotService timeSlotService;

    public TimeSlotController(TimeSlotService timeSlotService) {
        this.timeSlotService = timeSlotService;
    }

    @PostMapping("/timeslot")
    @Operation(summary = "Add a new time slot", description = "Adds a new time slot to the database")
    @ApiResponse(responseCode = "200", description = "Time slot added successfully")
    public void addTimeSlot(@RequestBody TimeSlotDto timeSlotDto) {
        timeSlotService.addTimeSlot(timeSlotDto);
    }

    @GetMapping("/all-timeslots")
    @Operation(summary = "Get all time slots", description = "Returns all time slots from the database")
    @ApiResponse(responseCode = "200", description = "Time slots fetched successfully")
    public List<TimeSlotDto> getAllTimeSlots() {
        return timeSlotService.getAllTimeSlots();
    }

    @PutMapping("/timeslot{id}")
    @Operation(summary = "Update a time slot", description = "Updates a time slot by id")
    @ApiResponse(responseCode = "200", description = "Time slot updated successfully")
    @ApiResponse(responseCode = "400",
            description = "Invalid request body: payload validation failed",
            content = @Content(schema = @Schema(implementation = ApiError.class)))
    @ApiResponse(responseCode = "404",
            description = "Booking not found / LessonType, StartDatetime or customer not found",
            content = @Content(schema = @Schema(implementation = ApiError.class)))
    public void updateTimeSlot(@PathVariable Integer id, @RequestBody @Valid TimeSlotDto timeSlotDto) {
        timeSlotService.updateTimeSlot(id, timeSlotDto);
    }

    @DeleteMapping("/timeslot{id}")
    @Operation(summary = "Delete time slot", description = "Deletes time slot from the database")
    @ApiResponse(responseCode = "200", description = "Time slot deleted successfully")
    public void deleteTimeSlot(@PathVariable Integer id) {
        timeSlotService.deleteTimeSlot(id);
    }


}