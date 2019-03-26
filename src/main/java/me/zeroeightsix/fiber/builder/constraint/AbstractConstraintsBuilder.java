package me.zeroeightsix.fiber.builder.constraint;

import me.zeroeightsix.fiber.constraint.Constraint;
import me.zeroeightsix.fiber.constraint.Constraints;
import me.zeroeightsix.fiber.constraint.NumberConstraint;

import java.util.ArrayList;
import java.util.List;

abstract class AbstractConstraintsBuilder<T> {

	final List<Constraint<T>> sourceConstraints;
	protected final Class<T> type;

	final List<Constraint<T>> newConstraints = new ArrayList<>();

	AbstractConstraintsBuilder(List<Constraint<T>> sourceConstraints, Class<T> type) {
		this.sourceConstraints = sourceConstraints;
		this.type = type;
	}

	void addConstraints() {
		sourceConstraints.addAll(newConstraints);
	}

}
