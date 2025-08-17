package ee.tiiukuusik.lessonscheduler.persistence.timeslot;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

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
    private LocalDateTime startDatetime;

    @NotNull
    @Column(name = "END_DATETIME", nullable = false)
    private LocalDateTime endDatetime;

    @NotNull
    @Column(name = "IS_AVAILABLE", nullable = false)
    private Boolean isAvailable = false;

}
