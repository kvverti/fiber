package me.zeroeightsix.fiber.builder.constraint;

import me.zeroeightsix.fiber.builder.ConfigValueBuilder;
import me.zeroeightsix.fiber.constraint.CompositeType;
import me.zeroeightsix.fiber.constraint.Constraint;

import java.util.List;

public class NumericalConstraintsBuilder<T extends Number>
		extends ConstraintsBuilder<T> implements NumericalConstraints<T> {
	public NumericalConstraintsBuilder(List<Constraint<T>> sourceConstraints, Class<T> type, ConfigValueBuilder<T> source) {
		super(sourceConstraints, type, source);
	}

	@Override
	public CompositeNumericalConstraintBuilder<T> composite(CompositeType type) {
		return new CompositeNumericalConstraintBuilder<T>(type, sourceConstraints, this.type, this);
	}


	public ConfigValueBuilder<T> finish() {
		super.addConstraints();
		return source;
	}

	public NumericalConstraintsBuilder<T> min(T min) {
		addNumericalLowerBound(min, newConstraints);
		return this;
	}

	public NumericalConstraintsBuilder<T> max(T min) {
		addNumericalUpperBound(min, newConstraints);
		return this;
	}
}
