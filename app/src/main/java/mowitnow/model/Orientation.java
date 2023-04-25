package mowitnow.model;

import java.util.Arrays;

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

   /* public static Orientation getOrientation (int angle){
        int degreeModule;
        if (angle>=360)
            degreeModule=angle%360;
        else if (angle<0)
            degreeModule = (angle%360 + 360) %360;
        else
            degreeModule =angle;
        return Arrays.stream(Orientation.values())
                .filter(a->degreeModule==a.getAngle())
                .findFirst()
                .orElseThrow(()-> new IllegalStateException("Unexpected value: " + angle));
    }*/
   public static Orientation getOrientation(int angle) {
       int degreeModule = (angle % 360 + 360) % 360;
       return Orientation.fromAngle(degreeModule);
   }
    public static Orientation fromAngle(int angle) {
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
