package ee.tiiukuusik.lessonScheduler.infrastructure.rest.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.security.cert.CertificateRevokedException;

@AllArgsConstructor
@Getter
public enum Error {
    BOOKING_DOES_NOT_EXIST("No booking found with entered id"),
    START_TIME_DOES_NOT_EXIST("Start time does not exist"),
    LESSON_TYPE_DOES_NOT_EXIST("Lesson type does not exist"),
    CUSTOMER_DOES_NOT_EXIST("Customer does not exist");
    private final String message;
}
