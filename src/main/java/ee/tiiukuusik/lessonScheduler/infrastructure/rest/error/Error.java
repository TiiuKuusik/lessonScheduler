package ee.tiiukuusik.lessonScheduler.infrastructure.rest.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Error {
    BOOKING_DOES_NOT_EXIST("No booking found with entered id");

    private final String message;
}
