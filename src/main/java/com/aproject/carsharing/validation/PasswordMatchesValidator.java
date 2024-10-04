package com.aproject.carsharing.validation;

import com.aproject.carsharing.dto.user.UserRegistrationRequestDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext context) {
        if (object instanceof UserRegistrationRequestDto requestDto) {
            return requestDto.getPassword().equals(requestDto.getRepeatPassword());
        }
        return false;
    }
}
