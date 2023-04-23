package mowitnow.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Tondeuse {
    private Coordinate currentCoordinate;
    private Orientation currentOrientation;
}
