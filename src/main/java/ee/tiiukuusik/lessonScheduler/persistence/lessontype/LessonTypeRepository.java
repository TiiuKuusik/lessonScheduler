package ee.tiiukuusik.lessonScheduler.persistence.lessontype;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonTypeRepository extends JpaRepository<LessonType, Integer> {
}