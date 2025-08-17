package ee.tiiukuusik.lessonscheduler.persistence.timeslot;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.time.LocalDateTime;
import java.util.Optional;

public interface TimeSlotRepository extends JpaRepository<TimeSlot, Integer> {

  @Query("select t from TimeSlot t where t.startDatetime = :startDatetime")
  Optional<TimeSlot> findByStartDatetime(LocalDateTime startDatetime);
}