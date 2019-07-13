package me.zeroeightsix.fiber.annotation.constraint;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import me.zeroeightsix.fiber.constraint.LengthConstraint;

/**
 * A constraint that restricts the length of certain elements.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@ConstraintClass(LengthConstraint.class)
public @interface Length {

    /**
     * The minimum length, inclusive, defaulting to no minimum length.
     */
    int min() default 0;

    /**
     * The maximum length, inclusive, defaulting to no maximum length.
     */
    int max() default Integer.MAX_VALUE;
}
