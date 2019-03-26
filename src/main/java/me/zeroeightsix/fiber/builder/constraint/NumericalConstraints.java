package me.zeroeightsix.fiber.builder.constraint;

import me.zeroeightsix.fiber.constraint.Constraint;
import me.zeroeightsix.fiber.constraint.Constraints;
import me.zeroeightsix.fiber.constraint.NumberConstraint;

import java.util.ArrayList;
import java.util.List;

interface NumericalConstraints<T extends Number> {
	default void addNumericalLowerBound(T bound, List<Constraint<T>> newConstraints) {
		checkNumerical(bound);
		newConstraints.add(new NumberConstraint<>(Constraints.NUMERICAL_LOWER_BOUND, bound));
	}

	default void addNumericalUpperBound(T bound, List<Constraint<T>> newConstraints) {
		checkNumerical(bound);
		newConstraints.add(new NumberConstraint<>(Constraints.NUMERICAL_UPPER_BOUND, bound));
	}

	default void checkNumerical(T value) {
		if (!Number.class.isAssignableFrom(value.getClass()))
			throw new IllegalStateException("Can't apply numerical constraint to non-numerical setting");
	}
}
