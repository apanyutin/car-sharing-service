package com.aproject.carsharing.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class UserUpdateRequestDto {
    @NotBlank(message = "FirstName can't be blank")
    @Length(max = 255, message = "FirstName can't be longer than 255")
    private String firstName;
    @NotBlank(message = "LastName can't be blank")
    @Length(max = 255, message = "LastName can't be longer than 255")
    private String lastName;
    @NotBlank(message = "Email can't be blank")
    @Email(message = "Incorrect email structure")
    @Length(max = 255, message = "Email can't be longer than 255")
    private String email;
}
