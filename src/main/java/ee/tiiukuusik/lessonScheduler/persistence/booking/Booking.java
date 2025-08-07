package ee.tiiukuusik.lessonScheduler.persistence.booking;

import ee.tiiukuusik.lessonScheduler.persistence.lessontype.LessonType;
import ee.tiiukuusik.lessonScheduler.persistence.timeslot.TimeSlot;
import ee.tiiukuusik.lessonScheduler.persistence.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "BOOKING")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "BOOKING_DATE", nullable = false)
    private Instant bookingDate;

    @Size(max = 20)
    @NotNull
    @ColumnDefault("'pending'")
    @Column(name = "STATUS", nullable = false, length = 20)
    private String status;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "TIME_SLOT_ID", nullable = false)
    private TimeSlot timeSlot;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "LESSON_TYPE_ID", nullable = false)
    private LessonType lessonType;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

}