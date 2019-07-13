package me.zeroeightsix.fiber.constraint;

import me.zeroeightsix.fiber.annotation.constraint.Range;

public class RangeConstraint extends Constraint<Number> {

	private final double min;
	private final double max;

	public RangeConstraint(Range data) {
		super(Constraints.RANGE, Number.class);
		if(data.max() < data.min()) {
			throw new IllegalArgumentException("Illegal range");
		}
		this.min = data.min();
		this.max = data.max();
	}

	@Override
	public boolean test(Number value) {
		double v = value.doubleValue();
		return min <= v && v <= max;
	}

}
