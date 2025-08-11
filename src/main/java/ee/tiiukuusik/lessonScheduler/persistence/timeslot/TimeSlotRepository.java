package ee.tiiukuusik.lessonScheduler.persistence.timeslot;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.Instant;
import java.util.Optional;

public interface TimeSlotRepository extends JpaRepository<TimeSlot, Integer> {


  @Query("SELECT t FROM TimeSlot t WHERE " +
          "YEAR(t.startDatetime) = YEAR(?1) AND " +
          "MONTH(t.startDatetime) = MONTH(?1) AND " +
          "DAY(t.startDatetime) = DAY(?1) AND " +
          "HOUR(t.startDatetime) = HOUR(?1) AND " +
          "MINUTE(t.startDatetime) = MINUTE(?1)")
  Optional<TimeSlot> findByStartDatetimeIgnoreSeconds(Instant startDatetime);
}