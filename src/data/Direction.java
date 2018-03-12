package data;

public enum Direction {
	UP(-1), DOWN(1), LEFT(-1), RIGHT(1), NOTSET(0);
	private final int value;

	Direction(int value) {
		this.value = value;
	}

	int getValue() {
		return value;
	}
}
