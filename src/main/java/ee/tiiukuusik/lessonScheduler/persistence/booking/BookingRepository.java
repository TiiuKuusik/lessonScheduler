package ee.tiiukuusik.lessonScheduler.persistence.booking;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
    List<Booking> id(Integer id);
}