package ee.tiiukuusik.lessonscheduler.service.lessontype;

import ee.tiiukuusik.lessonscheduler.controller.lessontype.dto.LessonTypeDto;
import ee.tiiukuusik.lessonscheduler.infrastructure.rest.error.Error;
import ee.tiiukuusik.lessonscheduler.infrastructure.rest.exception.DataNotFoundException;
import ee.tiiukuusik.lessonscheduler.infrastructure.rest.exception.ForbiddenException;
import ee.tiiukuusik.lessonscheduler.persistence.booking.BookingRepository;
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
    private final BookingRepository bookingRepository;

    public void addLessonType(LessonTypeDto lessonTypeDto) {
        LessonType lessonType = lessonTypeMapper.toLessonType(lessonTypeDto);
        lessonTypeRepository.save(lessonType);
    }

    public LessonTypeDto getLessonTypeById(Integer id) {
        LessonType lessonType = lessonTypeRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(Error.LESSON_TYPE_DOES_NOT_EXIST.getMessage()));
        return lessonTypeMapper.toLessonTypeDto(lessonType);
    }

    public List<LessonTypeDto> getAllLessonTypes() {
        List<LessonType> lessonTypes = lessonTypeRepository.findAll();
        return lessonTypeMapper.toLessonTypeDtos(lessonTypes);
    }

    public void updateLessonType(Integer id, LessonTypeDto lessonTypeDto) {
        LessonType existingLessonType = lessonTypeRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(Error.LESSON_TYPE_DOES_NOT_EXIST.getMessage()));
        LessonType updatedLessonType = lessonTypeMapper.toLessonType(lessonTypeDto);
        updatedLessonType.setId(existingLessonType.getId());
        LessonType savedLessonType = lessonTypeRepository.save(updatedLessonType);
        lessonTypeMapper.toLessonTypeDto(savedLessonType);
    }

    public void deleteLessonType(Integer id) {
        if (!lessonTypeRepository.existsById(id)) {
            throw new DataNotFoundException(Error.LESSON_TYPE_DOES_NOT_EXIST.getMessage());
        }
        if (hasRelatedBookings(id)) {
            throw new ForbiddenException(Error.LESSON_TYPE_HAS_ACTIVE_BOOKINGS.getMessage());
        }
        lessonTypeRepository.deleteById(id);
    }

    private boolean hasRelatedBookings(Integer lessonTypeId) {
        return bookingRepository.existsByLessonTypeId(lessonTypeId);
    }
}