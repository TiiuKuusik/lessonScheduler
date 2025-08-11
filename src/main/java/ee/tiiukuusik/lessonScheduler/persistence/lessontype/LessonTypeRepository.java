package ee.tiiukuusik.lessonScheduler.persistence.lessontype;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface LessonTypeRepository extends JpaRepository<LessonType, Integer> {


    @Query("select l from LessonType l where upper(l.typeName) = upper(:typeName)")
    Optional<LessonType> findLessonTypeBy(String typeName);
}