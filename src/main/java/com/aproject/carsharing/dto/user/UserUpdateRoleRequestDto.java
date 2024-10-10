package com.aproject.carsharing.dto.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateRoleRequestDto {
    @NotBlank(message = "RoleName can't be blank")
    private String roleName;
}
