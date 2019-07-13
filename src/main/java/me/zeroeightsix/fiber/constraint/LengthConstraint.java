package me.zeroeightsix.fiber.constraint;

import me.zeroeightsix.fiber.annotation.constraint.Length;

public class LengthConstraint extends Constraint<String> {

    private final int min;
    private final int max;

    public LengthConstraint(Length data) {
        super(Constraints.STRING_LENGTH, String.class);
        if(data.min() < 0 || data.max() < data.min()) {
            throw new IllegalArgumentException("Invalid range");
        }
        this.min = data.min();
        this.max = data.max();
    }

    @Override
    public boolean test(String value) {
        int len = value.length();
        return min <= len && len <= max;
    }
}
