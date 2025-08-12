package ee.tiiukuusik.lessonscheduler.controller.lessontype.dto;

import ee.tiiukuusik.lessonscheduler.persistence.lessontype.LessonType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link LessonType}
 */
@Value
public class LessonTypeDto implements Serializable {
    @NotNull
    @Size(max = 20)
    String typeName;
    
    @NotNull
    BigDecimal price;
    
    @NotNull
    @Size(max = 100)
    String description;
    
    @NotNull
    Integer durationMinutes;
}
