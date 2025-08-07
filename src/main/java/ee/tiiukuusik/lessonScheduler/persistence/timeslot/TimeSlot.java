package ee.tiiukuusik.lessonScheduler.persistence.timeslot;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "TIME_SLOT")
public class TimeSlot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "START_DATETIME", nullable = false)
    private Instant startDatetime;

    @NotNull
    @Column(name = "END_DATETIME", nullable = false)
    private Instant endDatetime;

    @NotNull
    @Column(name = "IS_AVAILABLE", nullable = false)
    private Boolean isAvailable = false;

}