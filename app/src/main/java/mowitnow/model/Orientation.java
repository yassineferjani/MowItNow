package mowitnow.model;

public enum Orientation {
    NORTH('N', 0),
    EAST('E', 90),
    WEST('W', 270),
    SOUTH('S', 180);
    private final char symbol;
    private final int angle;
    private Orientation(char symbol, int angle) {
        this.symbol = symbol;
        this.angle = angle;
    }
    public char getSymbol() {
        return symbol;
    }
    public int getAngle() {
        return angle;
    }
    public static Orientation getOrientation(int angle) {
        return switch (angle) {
            case 0->NORTH;
            case 90->EAST;
            case 180->SOUTH;
            case 270->WEST;
            default -> throw new IllegalStateException("Unexpected value: " + angle);
        };
    }

}
