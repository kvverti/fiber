package me.zeroeightsix.fiber.constraint;

public abstract class Constraint<A> { // A is the type of values we're gonna check

	private final Constraints type;
	private final Class<?> valueType;

	public Constraint(Constraints type, Class<?> valueType) {
		this.type = type;
		this.valueType = valueType;
	}

	public Class<?> getValueType() {
		return valueType;
	}

	public Constraints getType() {
		return type;
	}

	public abstract boolean test(A value);

}
