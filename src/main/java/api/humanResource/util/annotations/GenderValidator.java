package api.humanResource.util.annotations;

import api.humanResource.model.enums.Gender;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class GenderValidator implements ConstraintValidator<GenderValidation, Gender> {


    @Override
    public boolean isValid(Gender gender, ConstraintValidatorContext constraintValidatorContext) {


        return (gender == Gender.MALE || gender == Gender.FEMALE);

    }
}
