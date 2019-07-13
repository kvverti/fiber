package me.zeroeightsix.fiber.builder.constraint;

import me.zeroeightsix.fiber.constraint.Constraint;

import java.util.ArrayList;
import java.util.List;

abstract class AbstractConstraintsBuilder<T> {

	final List<Constraint<? super T>> sourceConstraints;
	protected final Class<T> type;

	final List<Constraint<? super T>> newConstraints = new ArrayList<>();

	AbstractConstraintsBuilder(List<Constraint<? super T>> sourceConstraints, Class<T> type) {
		this.sourceConstraints = sourceConstraints;
		this.type = type;
	}

	void addNewConstraint(Constraint<? super T> constraint) {
		newConstraints.add(constraint);
	}

	void addConstraints() {
		sourceConstraints.addAll(newConstraints);
	}

}
