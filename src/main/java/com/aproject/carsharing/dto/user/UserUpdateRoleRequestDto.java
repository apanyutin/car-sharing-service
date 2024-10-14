package com.aproject.carsharing.dto.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class UserUpdateRoleRequestDto {
    @NotBlank(message = "RoleName can't be blank")
    private String roleName;
}
