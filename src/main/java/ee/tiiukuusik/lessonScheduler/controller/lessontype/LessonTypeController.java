package ee.tiiukuusik.lessonscheduler.controller.lessontype;

import ee.tiiukuusik.lessonscheduler.controller.lessontype.dto.LessonTypeDto;
import ee.tiiukuusik.lessonscheduler.service.lessontype.LessonTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class LessonTypeController {
    
    private final LessonTypeService lessonTypeService;
    
    public LessonTypeController(LessonTypeService lessonTypeService) {
        this.lessonTypeService = lessonTypeService;
    }
    
    @PostMapping("/lesson-type")
    @Operation(summary = "Add a new lesson type", description = "Adds a new lesson type to the database")
    @ApiResponse(responseCode = "200", description = "Lesson type added successfully")
    public void addLessonType(@RequestBody @Valid LessonTypeDto lessonTypeDto) {
        lessonTypeService.addLessonType(lessonTypeDto);
    }
    
    @GetMapping("/lesson-type/{id}")
    @Operation(summary = "Get lesson type by ID", description = "Retrieves a lesson type by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lesson type found"),
        @ApiResponse(responseCode = "404", description = "Lesson type not found")
    })
    public LessonTypeDto getLessonTypeById(@PathVariable Integer id) {
        return lessonTypeService.getLessonTypeById(id);
    }
    
    @GetMapping("/lesson-type/all")
    @Operation(summary = "Get all lesson types", description = "Retrieves all lesson types from the database")
    @ApiResponse(responseCode = "200", description = "List of lesson types retrieved successfully")
    public List<LessonTypeDto> getAllLessonTypes() {
        return lessonTypeService.getAllLessonTypes();
    }
    
    @PutMapping("/lesson-type/{id}")
    @Operation(summary = "Update lesson type", description = "Updates an existing lesson type")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lesson type updated successfully"),
        @ApiResponse(responseCode = "404", description = "Lesson type not found")
    })
    public void updateLessonType(@PathVariable Integer id, @RequestBody @Valid LessonTypeDto lessonTypeDto) {
        lessonTypeService.updateLessonType(id, lessonTypeDto);
    }
    
    @DeleteMapping("/lesson-type/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete lesson type", description = "Deletes a lesson type by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Lesson type deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Lesson type not found")
    })
    public void deleteLessonType(@PathVariable Integer id) {
        lessonTypeService.deleteLessonType(id);
    }
}
