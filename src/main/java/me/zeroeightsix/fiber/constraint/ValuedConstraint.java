package me.zeroeightsix.fiber.constraint;

public abstract class ValuedConstraint<T, A> extends Constraint<A> { // A is the type of values we're gonna check

	private final T value;

	public ValuedConstraint(Constraints type, Class<?> valueType, T value) {
		super(type, valueType);
		this.value = value;
	}

	public T getValue() {
		return value;
	}

}
