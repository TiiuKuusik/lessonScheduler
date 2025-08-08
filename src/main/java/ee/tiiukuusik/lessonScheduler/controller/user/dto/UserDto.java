package ee.tiiukuusik.lessonScheduler.controller.user.dto;

import ee.tiiukuusik.lessonScheduler.persistence.user.User;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link User}
 */
@Value
public class UserDto implements Serializable {
    Integer id;
    @NotNull
    @Size(max = 50)
    String firstName;
    @NotNull
    @Size(max = 50)
    String lastName;
    @NotNull
    @Size(max = 50)
    String phone;
    @NotNull
    @Size(max = 100)
    String email;
    @NotNull
    @Size(max = 10)
    String role;
}