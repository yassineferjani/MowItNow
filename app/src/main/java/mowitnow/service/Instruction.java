package mowitnow.service;

import mowitnow.exception.InvalidCoordinate;
import mowitnow.model.*;

import java.util.List;

import static mowitnow.model.Orientation.*;

public class Instruction {

    /** problème */
    public static Orientation rotateLeft(final Orientation orientation) {
        int nouvelleOrientation = orientation.getAngle() - 90;
        if (nouvelleOrientation < 0) {
            nouvelleOrientation += 360;
        }
        return getOrientation(nouvelleOrientation);
    }

    /** problème */
    public static Orientation rotateRight(final Orientation orientation) {
        int nouvelleOrientation = orientation.getAngle() + 90;
        if (nouvelleOrientation >= 360) {
            nouvelleOrientation -= 360;
        }
        return getOrientation(nouvelleOrientation);
    }

    /** Très bien */
    public static Coordinate avancer(Coordinate coordinate, final Orientation orientation) {
        return switch (orientation) {
            case NORTH -> Coordinate.builder().x(coordinate.x()).y(coordinate.y()+1).build();
            case EAST ->  Coordinate.builder().x(coordinate.x()+1).y(coordinate.y()).build();
            case SOUTH -> Coordinate.builder().x(coordinate.x()).y(coordinate.y()-1).build();
            case WEST ->  Coordinate.builder().x(coordinate.x()-1).y(coordinate.y()).build();
        };
    }

    /** A voir si on peut mieux faire */
    public static boolean checkCoordinate(final Coordinate coordinate, final List<Coordinate> pelouseTaken){
        return  pelouseTaken.stream().noneMatch(l->l.equals(coordinate));
    }

    /** side effects */
    public static void executeInstruction(final InstructionTondeuse instruction,
                                          final Tondeuse tondeuse,
                                          final List<Coordinate> pelouseTaken,
                                          final List<Coordinate> list) {
        Coordinate nextCoordinate;
        if (! checkCoordinate(tondeuse.getCurrentCoordinate(), pelouseTaken)) {
            throw new RuntimeException();
        }
        switch (instruction) {
            case Right -> tondeuse.setCurrentOrientation(rotateRight(tondeuse.getCurrentOrientation()));
            case Left -> tondeuse.setCurrentOrientation(rotateLeft(tondeuse.getCurrentOrientation()));
            case Forward -> {
                nextCoordinate = avancer(tondeuse.getCurrentCoordinate(),tondeuse.getCurrentOrientation());
                if (isCoordonneesValid(nextCoordinate, list)){
                    tondeuse.setCurrentCoordinate(nextCoordinate);
                }else {
                    throw new InvalidCoordinate("Invalid coordinate for this instruction : " + instruction);
                }
            }
        }
    }
}

