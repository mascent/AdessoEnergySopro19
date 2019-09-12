package de.sopro.data;

public enum Role {
	Admin, User, Shared;

	public String toString() {
		switch (this) {
		case Admin:
			return "Admin";
		case User:
			return "User";
		case Shared:
			return "Shared";

		default:
			return null;
		}
	}
}
