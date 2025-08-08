package ee.tiiukuusik.lessonScheduler.controller.timeslot;

import ee.tiiukuusik.lessonScheduler.controller.timeslot.dto.TimeSlotDto;
import ee.tiiukuusik.lessonScheduler.service.timeslot.TimeSlotService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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


}
