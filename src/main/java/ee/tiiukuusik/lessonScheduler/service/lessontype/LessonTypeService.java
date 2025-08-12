package ee.tiiukuusik.lessonscheduler.service.lessontype;

import ee.tiiukuusik.lessonscheduler.controller.lessontype.dto.LessonTypeDto;
import ee.tiiukuusik.lessonscheduler.infrastructure.rest.exception.DataNotFoundException;
import ee.tiiukuusik.lessonscheduler.persistence.lessontype.LessonType;
import ee.tiiukuusik.lessonscheduler.persistence.lessontype.LessonTypeMapper;
import ee.tiiukuusik.lessonscheduler.persistence.lessontype.LessonTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonTypeService {
    private final LessonTypeRepository lessonTypeRepository;
    private final LessonTypeMapper lessonTypeMapper;

    public void addLessonType(LessonTypeDto lessonTypeDto) {
        LessonType lessonType = lessonTypeMapper.toLessonType(lessonTypeDto);
        lessonTypeRepository.save(lessonType);
    }
    
    public LessonTypeDto getLessonTypeById(Integer id) {
        LessonType lessonType = lessonTypeRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Lesson type not found with id: " + id));
        return lessonTypeMapper.toLessonTypeDto(lessonType);
    }
    
    public List<LessonTypeDto> getAllLessonTypes() {
        List<LessonType> lessonTypes = lessonTypeRepository.findAll();
        return lessonTypeMapper.toLessonTypeDtos(lessonTypes);
    }
    
    public LessonTypeDto updateLessonType(Integer id, LessonTypeDto lessonTypeDto) {
        LessonType existingLessonType = lessonTypeRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Lesson type not found with id: " + id));
        
        // Update the existing lesson type with values from the DTO
        LessonType updatedLessonType = lessonTypeMapper.toLessonType(lessonTypeDto);
        updatedLessonType.setId(existingLessonType.getId());
        
        LessonType savedLessonType = lessonTypeRepository.save(updatedLessonType);
        return lessonTypeMapper.toLessonTypeDto(savedLessonType);
    }
    
    public void deleteLessonType(Integer id) {
        if (!lessonTypeRepository.existsById(id)) {
            throw new DataNotFoundException("Lesson type not found with id: " + id);
        }
        lessonTypeRepository.deleteById(id);
    }
}
