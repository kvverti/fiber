package me.zeroeightsix.fiber.builder.constraint;

import me.zeroeightsix.fiber.constraint.CompositeType;
import me.zeroeightsix.fiber.constraint.Constraint;

import java.util.List;

public final class CompositeNumericalConstraintBuilder<T extends Number>
		extends CompositeConstraintBuilder<T>
		implements NumericalConstraints<T> {
	public CompositeNumericalConstraintBuilder(CompositeType compositeType, List<Constraint<T>> sourceConstraints, Class<T> type, ConstraintsBuilder<T> source) {
		super(compositeType, sourceConstraints, type, source);
	}

	public CompositeNumericalConstraintBuilder<T> min(T min) {
		addNumericalLowerBound(min, newConstraints);
		return this;
	}

	public CompositeNumericalConstraintBuilder<T> max(T min) {
		addNumericalUpperBound(min, newConstraints);
		return this;
	}
}
