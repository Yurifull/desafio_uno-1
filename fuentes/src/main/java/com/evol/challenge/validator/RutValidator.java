package com.evol.challenge.validator;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Rut validator class, used to validate the RUT format. Validates chilean RUTs.
 */
public class RutValidator implements ConstraintValidator<ValidRut, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }

        value = value.toUpperCase();
        value = value.replace(".", "").replace("-", "");

        int rutAux = Integer.parseInt(value.substring(0, value.length() - 1));
        char dv = value.charAt(value.length() - 1);

        int m = 0, s = 1;
        for (; rutAux != 0; rutAux /= 10) {
            s = (s + rutAux % 10 * (9 - m++ % 6)) % 11;
        }

        return dv == (char) (s != 0 ? s + 47 : 75);
    }
}