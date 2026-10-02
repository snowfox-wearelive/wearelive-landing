package band.wearelive.landing.common.web;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** Marks a pre-registration REST controller so it gets the JSON error handling in {@link ApiExceptionHandler}. */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface PreRegistrationApi {
}
