package ee.tiiukuusik.lessonScheduler.controller.timeslot;

import ee.tiiukuusik.lessonScheduler.controller.dto.TimeSlotDto;
import ee.tiiukuusik.lessonScheduler.service.timeslot.TimeSlotService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class TimeSlotController {

    private final TimeSlotService timeSlotService;

    public TimeSlotController(TimeSlotService timeSlotService) {
        this.timeSlotService = timeSlotService;
    }

    @PostMapping("/timeslot")
    public void addTimeSlot(@RequestBody TimeSlotDto timeSlotDto) {
        timeSlotService.addTimeSlot(timeSlotDto);
    }



}
