package ee.tiiukuusik.lessonscheduler.persistence.lessontype;

import ee.tiiukuusik.lessonscheduler.controller.lessontype.dto.LessonTypeDto;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper
public interface LessonTypeMapper {
    LessonType toLessonType(LessonTypeDto lessonTypeDto);

    LessonTypeDto toLessonTypeDto(LessonType lessonType);

    List<LessonTypeDto> toLessonTypeDtos(List<LessonType> lessonTypes);
}
