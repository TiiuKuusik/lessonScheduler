package ee.tiiukuusik.lessonScheduler.persistence.user;

import ee.tiiukuusik.lessonScheduler.controller.user.dto.UserDto;

import java.util.List;

public interface UserMapper {
    User toEntity(UserDto userDto);

    UserDto toDto(User user);

    List<UserDto> toDtoList(List<User> users);

    List<User> toEntityList(List<UserDto> userDtos);
}
