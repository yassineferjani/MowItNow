package mowitnow.service;

import lombok.Builder;
import lombok.Data;
import mowitnow.model.Coordinate;
import mowitnow.model.Movement;
import mowitnow.model.Orientation;
import mowitnow.model.Lawn;

@Data
@Builder
public class MoveMow {
    Orientation currentOrientation;
    Coordinate currentCoordinate;
    Lawn map;
    public static Orientation getNextOrientation(Movement movement, Orientation previousOrientation){
        return switch (movement){
            case Forward -> previousOrientation;
            case Left -> Orientation.getOrientation(previousOrientation.getAngle()-90);
            case Right -> Orientation.getOrientation(previousOrientation.getAngle()+90);
        };
    }

    private static Coordinate forward(final Coordinate coordinate, final Orientation orientation) {
        return switch (orientation) {
            case NORTH -> Coordinate.builder().x(coordinate.x()).y(coordinate.y()+1).build();
            case EAST ->  Coordinate.builder().x(coordinate.x()+1).y(coordinate.y()).build();
            case SOUTH -> Coordinate.builder().x(coordinate.x()).y(coordinate.y()-1).build();
            case WEST ->  Coordinate.builder().x(coordinate.x()-1).y(coordinate.y()).build();
        };
    }

    public void execute(final Movement instruction) {
        if (!Movement.Forward.equals(instruction))
            currentOrientation = getNextOrientation(instruction,currentOrientation);
        else {
            Coordinate nextCoordinate = forward(currentCoordinate,currentOrientation);
            if (isCoordinateFree(nextCoordinate)){
                map.free(currentCoordinate);
                map.block(nextCoordinate);
                currentCoordinate=nextCoordinate;
            }
        }
    }
    private boolean isCoordinateFree(Coordinate coordinate){
        return map.isInBound(coordinate) && map.isFree(coordinate);
    }
}

