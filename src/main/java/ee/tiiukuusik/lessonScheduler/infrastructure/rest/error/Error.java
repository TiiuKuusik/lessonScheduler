package ee.tiiukuusik.lessonscheduler.infrastructure.rest.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Error {
    BOOKING_DOES_NOT_EXIST("No booking found with entered id"),
    START_TIME_DOES_NOT_EXIST("Time slot with entered start time does not exist"),
    LESSON_TYPE_DOES_NOT_EXIST("Lesson type does not exist"),
    TIME_SLOT_IS_BOOKED("Time slot is booked"),
    TIME_SLOT_DOES_NOT_EXIST("Time slot with entered id does not exist"),
    CUSTOMER_HAS_ACTIVE_BOOKINGS("Customer cannot be deleted because of pending or confirmed bookings"),
    CUSTOMER_DOES_NOT_EXIST("Customer does not exist");
    private final String message;
}
