package mowitnow.model;

public enum Orientation {
    NORTH(0),
    EAST(90),
    WEST( 270),
    SOUTH( 180);
    private final int angle;
    private Orientation( int angle) {
        this.angle = angle;
    }

    public int getAngle() {
        return angle;
    }

   public static Orientation getOrientation(int angle) {
       int nextAngle = angle - (angle / 360) * 360;
       if (nextAngle < 0) {
           nextAngle += 360;
       }
       return Orientation.fromAngle(nextAngle);
   }
    private static Orientation fromAngle(int angle) {
        switch (angle) {
            case 0: return NORTH;
            case 90: return EAST;
            case 180: return SOUTH;
            case 270: return WEST;
            default:
                throw new IllegalArgumentException("Invalid angle: " + angle);
        }
    }
}
