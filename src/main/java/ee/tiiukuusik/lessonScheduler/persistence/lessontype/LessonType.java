package ee.tiiukuusik.lessonscheduler.persistence.lessontype;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "LESSON_TYPE")
public class LessonType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Size(max = 20)
    @NotNull
    @Column(name = "TYPE_NAME", nullable = false, length = 20)
    private String typeName;

    @NotNull
    @Column(name = "PRICE", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Size(max = 100)
    @NotNull
    @Column(name = "DESCRIPTION", nullable = false, length = 100)
    private String description;

    @NotNull
    @ColumnDefault("120")
    @Column(name = "DURATION_MINUTES", nullable = false)
    private Integer durationMinutes;

}
