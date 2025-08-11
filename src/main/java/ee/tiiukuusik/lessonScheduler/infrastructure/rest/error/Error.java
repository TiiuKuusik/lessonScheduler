package ee.tiiukuusik.lessonScheduler.infrastructure.rest.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.security.cert.CertificateRevokedException;

@AllArgsConstructor
@Getter
public enum Error {
    BOOKING_DOES_NOT_EXIST("No booking found with entered id"),
    START_TIME_DOES_NOT_EXIST("Time slot with entered start time does not exist"),
    LESSON_TYPE_DOES_NOT_EXIST("Lesson type does not exist"),
    TIME_SLOT_IS_NOT_AVAILABLE("Time slot is not available"),
    CUSTOMER_DOES_NOT_EXIST("Customer does not exist");
    private final String message;
}
