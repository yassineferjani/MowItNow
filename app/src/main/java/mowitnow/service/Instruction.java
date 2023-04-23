package mowitnow.service;

import mowitnow.exception.InvalidCoordinate;
import mowitnow.model.*;

import java.util.List;

import static mowitnow.model.Orientation.*;

public class Instruction {

    public static Orientation rotateLeft(final Orientation orientation) {
        int nouvelleOrientation = orientation.getAngle() - 90;
        if (nouvelleOrientation < 0) {
            nouvelleOrientation += 360;
        }
        return getOrientation(nouvelleOrientation);
    }

    public static Orientation rotateRight(final Orientation orientation) {
        int nouvelleOrientation = orientation.getAngle() + 90;
        if (nouvelleOrientation >= 360) {
            nouvelleOrientation -= 360;
        }
        return getOrientation(nouvelleOrientation);
    }

    private static Coordinate avancer(Coordinate coordinate, final Orientation orientation) {
        Coordinate nextCoordinate = null;
        switch (orientation) {
            case NORTH -> nextCoordinate = Coordinate.builder().x(coordinate.x()).y(coordinate.y()+1).build();
            case EAST ->  nextCoordinate = Coordinate.builder().x(coordinate.x()+1).y(coordinate.y()).build();
            case SOUTH -> nextCoordinate = Coordinate.builder().x(coordinate.x()).y(coordinate.y()-1).build();
            case WEST ->  nextCoordinate = Coordinate.builder().x(coordinate.x()-1).y(coordinate.y()).build();
        }
        return nextCoordinate;
    }
    public static boolean checkCoordinate(final Coordinate coordinate, final List<Coordinate> pelouseTaken){
        return  pelouseTaken.stream().noneMatch(l->l.equals(coordinate));
    }
    public static int getMaxX(final List<Coordinate> coordonnees) {
        return coordonnees.stream()
                .mapToInt(Coordinate::x)
                .max()
                .orElse(0);
    }
    public static int getMaxY(final List<Coordinate> coordonnees) {
        return coordonnees.stream()
                .mapToInt(Coordinate::y)
                .max()
                .orElse(0);
    }
    public static boolean isCoordonneesValid(Coordinate coordinate, List<Coordinate> pelouse) {
            return coordinate.y()>= 0 && coordinate.y() <= getMaxY(pelouse)
                    && coordinate.x() >= 0 && coordinate.x() <= getMaxX(pelouse);
            }

    public static void executeInstruction(final InstructionTondeuse instruction,
                                          final Tondeuse tondeuse,
                                          final List<Coordinate> pelouseTaken,
                                          final Pelouse pelouse) {
        Coordinate nextCoordinate;
        if (! checkCoordinate(tondeuse.getCurrentCoordinate(), pelouseTaken)) {
            throw new RuntimeException();
        }
        switch (instruction) {
            case Right -> tondeuse.setCurrentOrientation(rotateRight(tondeuse.getCurrentOrientation()));
            case Left -> tondeuse.setCurrentOrientation(rotateLeft(tondeuse.getCurrentOrientation()));
            case Forward -> {
                nextCoordinate = avancer(tondeuse.getCurrentCoordinate(),tondeuse.getCurrentOrientation());
                if (isCoordonneesValid(nextCoordinate, pelouse.getCoordonnees())){
                    tondeuse.setCurrentCoordinate(nextCoordinate);
                }else {
                    throw new InvalidCoordinate("Invalid coordinate for this instruction : " + instruction);
                }
            }
        }
    }
}

