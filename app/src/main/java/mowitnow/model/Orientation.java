package mowitnow.model;

public enum Orientation {
    NORTH(0),
    EAST(90),
    WEST( 270),
    SOUTH( 180);
    private final int angle;
    Orientation( int angle) {
        this.angle = angle;
    }

    public int getAngle() {
        return angle;
    }

   private static int adjustAngle(int angle) {
       int nextAngle = angle - (angle / 360) * 360;
       if (nextAngle < 0) {
           nextAngle += 360;
       }
       return nextAngle;
   }
    public static Orientation get(int angle) {
        return switch (adjustAngle(angle)) {
            case 0 -> NORTH;
            case 90 -> EAST;
            case 180 -> SOUTH;
            case 270 -> WEST;
            default -> throw new IllegalArgumentException("Invalid angle: " + angle);
        };
    }
    public Orientation rotate(int angle){
        return get(this.angle + angle);
    }
}
