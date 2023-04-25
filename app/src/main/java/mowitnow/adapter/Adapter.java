package mowitnow.adapter;

import mowitnow.exception.InvalidInput;
import mowitnow.model.*;
import mowitnow.service.CheckInput;
import mowitnow.service.MoveMow;

import java.util.List;
import java.util.stream.Collectors;

import static mowitnow.model.Movement.*;
import static mowitnow.model.Orientation.*;

public class Adapter {
    public static Movement mapToMovement(char c) {
        return switch (c){
            case 'D'->Right;
            case 'A'->Forward;
            case 'G'->Left;
            default -> throw new IllegalStateException("Unexpected value: " + c);
        };
    }

    public static Orientation mapToOrientation(String s) {
        return switch (s){
            case "N"->NORTH;
            case "W"->WEST;
            case "E"->EAST;
            case "S"->SOUTH;
            default -> throw new IllegalStateException("Unexpected value: " + s);
        };
    }

    public static List<Movement> mapToListOfMovements(String s){
        if(!CheckInput.isValidMowerMovements(s))
            throw new InvalidInput("Invalid instructions  : " + s);
        return s
                .chars()
                .mapToObj(c->(char)c)
                .map(Adapter::mapToMovement)
                .collect(Collectors.toList());
    }
    public static Lawn maptoLawn(String s){
        if (!CheckInput.isValidLawerCordinate(s))
            throw new InvalidInput("Invalid pelouse coordinate : " + s);
        String[] dimension = s.split("\\s");
        int maxX = Integer.parseInt(dimension[0]);
        int maxY = Integer.parseInt(dimension[1]);
        return new Lawn(maxX,maxY);
    }

    public static MoveMow maptoMower(String s, Lawn map){
        if (!CheckInput.isValidMowerPosition(s))
            throw new InvalidInput("Invalid pelouse coordinate : " + s);
        String[] dimension = s.split("\\s");
        int x = Integer.parseInt(dimension[0]);
        int y = Integer.parseInt(dimension[1]);
        Orientation orientation = mapToOrientation(dimension[2]);
        Coordinate coordinate = Coordinate.builder()
                .x(x)
                .y(y)
                .build();
        return MoveMow.builder()
                .currentCoordinate(coordinate)
                .currentOrientation(orientation)
                .map(map)
                .build();
    }

    public static String mapFromOrientation(Orientation orientation) {
        return switch (orientation){
            case NORTH->"N";
            case EAST->"E";
            case SOUTH->"S";
            case WEST->"W";
        };
    }
}
