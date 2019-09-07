package de.sopro.data;

public enum Role {
	Admin, User;

	public String toString() {
		switch (this) {
		case Admin:
			return "Admin";
		case User:
			return "User";

		default:
			return null;
		}
	}
}
