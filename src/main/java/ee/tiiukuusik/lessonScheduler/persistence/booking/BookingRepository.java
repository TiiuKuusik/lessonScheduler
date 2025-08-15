package ee.tiiukuusik.lessonscheduler.persistence.booking;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
    List<Booking> id(Integer id);

    List<Booking> findByCustomerId(Integer id);
}
