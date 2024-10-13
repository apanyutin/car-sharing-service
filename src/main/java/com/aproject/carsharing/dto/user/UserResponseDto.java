package com.aproject.carsharing.dto.user;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class UserResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Long tgChatId;
}
