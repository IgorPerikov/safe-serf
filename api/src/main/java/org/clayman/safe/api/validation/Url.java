package org.clayman.safe.api.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UrlValidator.class)
public @interface Url {

    String message() default "{org.clayman.safe.api.url.validation.error}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
