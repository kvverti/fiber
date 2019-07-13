package me.zeroeightsix.fiber.annotation.constraint;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import me.zeroeightsix.fiber.constraint.RangeConstraint;

/**
 * A constraint that restricts values to a closed numeric range. This constraint
 * may allow Infinity, but it does not allow NaN.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@ConstraintClass(RangeConstraint.class)
public @interface Range {

    /**
     * The minimum value. The default is no minimum.
     */
    double min() default Double.NEGATIVE_INFINITY;

    /**
     * The maximum. The default is no maximum.
     */
    double max() default Double.POSITIVE_INFINITY;
}
