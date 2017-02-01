package org.clayman.safe.api.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UrlValidator implements ConstraintValidator<Url, String> {

    @Override
    public void initialize(Url constraintAnnotation) {}

    // TODO: meh
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        int i = value.indexOf("http://");
        int i2 = value.indexOf("https://");
        int www = value.indexOf("www");
        if ((i != -1 && www != -1 && www > i) || (i2 != -1 && www != -1 && www > i2)) {
            return true;
        } else {
            return false;
        }
    }
}
