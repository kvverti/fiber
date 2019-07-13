package me.zeroeightsix.fiber.constraint;

import me.zeroeightsix.fiber.Identifier;

public enum Constraints {
	RANGE(false, identifier("range")),
	STRING_LENGTH(false, identifier("length")),
	STRING_STARTING_WITH(false, identifier("starts_with")),
	STRING_ENDING_WITH(false, identifier("ends_with")),
	FINAL(false, identifier("final")),
	COMPOSITE(false, identifier("composite"));

	private final boolean numerical;
	private final Identifier identifier;

	Constraints(boolean numerical, Identifier identifier) {
		this.numerical = numerical;
		this.identifier = identifier;
	}

	private static Identifier identifier(String name) {
		return new Identifier("fabric", name);
	}

	public boolean isNumerical() {
		return numerical;
	}

	public Identifier getIdentifier() {
		return identifier;
	}

}
