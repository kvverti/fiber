package me.zeroeightsix.fiber.builder;

import me.zeroeightsix.fiber.builder.constraint.NumericalConstraintsBuilder;
import me.zeroeightsix.fiber.ir.ConfigNode;

public class NumericalConfigValueBuilder<T extends Number> extends ConfigValueBuilder<T> {
    public NumericalConfigValueBuilder(ConfigNode registry, Class<T> type) {
        super(registry, type);
    }

    public NumericalConstraintsBuilder<T> constraints() {
        return new NumericalConstraintsBuilder<>(constraints, type, this);
    }
}
