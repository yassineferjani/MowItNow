package mowitnow.adapter;

import mowitnow.model.InstructionTondeuse;
import mowitnow.model.Orientation;

import java.util.ArrayList;
import java.util.List;

public class Adapter {
    public static InstructionTondeuse getInstruction(char tInstruction) {
        for (InstructionTondeuse instructionTondeuse : InstructionTondeuse.values()) {
            if (instructionTondeuse.getCodeInstruction() == tInstruction) {
                return instructionTondeuse;
            }
        }
        return null;
    }

    public static Orientation getOrientation(char tOrientation) {
        for (Orientation orientation : Orientation.values()) {
            if (orientation.getSymbol() == tOrientation) {
                return orientation;
            }
        }
        return null;
    }

}
