package me.zeroeightsix.fiber.annotation.constraint;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import me.zeroeightsix.fiber.constraint.Constraint;

/**
 * Placed on constraint annotations to tell the API which constraint class
 * corresponds to which annotation type.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface ConstraintClass {

    /**
     * The concrete class that implements the constraint. The class must be
     * concrete and have a publically visible constructor that takes in one
     * parameter of the annotated type.
     */
    @SuppressWarnings("rawtypes")
    Class<? extends Constraint> value();
}
