package com.aproject.carsharing.car_sharing_service.dto.user;

import com.aproject.carsharing.car_sharing_service.validation.PasswordMatches;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@PasswordMatches
public class UserRegistrationRequestDto {
    @NotBlank(message = "FirstName can't be blank")
    @Length(max = 255, message = "FirstName can't be longer than 255")
    private String firstName;
    @NotBlank(message = "LastName can't be blank")
    @Length(max = 255, message = "LastName can't be longer than 255")
    private String lastName;
    @NotBlank(message = "Email can't be blank")
    @Email(message = "Incorrect email")
    @Length(max = 255, message = "Email can't be longer than 255")
    private String email;
    @NotBlank(message = "Password can't be blank")
    @Length(min = 8, max = 24, message = "Password must be between 8 and 24 characters long")
    @ToString.Exclude
    private String password;
    @NotBlank(message = "RepeatPassword can't be blank")
    @Length(min = 8, max = 24, message = "RepeatPassword must be between 8 and 24 characters long")
    @ToString.Exclude
    private String repeatPassword;
}