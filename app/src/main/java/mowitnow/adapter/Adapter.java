package mowitnow.adapter;

import mowitnow.model.InstructionTondeuse;
import mowitnow.model.Orientation;

import java.util.ArrayList;
import java.util.List;

public class Adapter {

     //récuperer une liste d'enum InstructionTondeuse correspondante à la ligne d'instruction
    public static List<InstructionTondeuse> formaterLigneInstruction(String ligneInstruction) {
        List<InstructionTondeuse> listInstructionTondeuse = new ArrayList<InstructionTondeuse>();
        for (char instruction : ligneInstruction.toCharArray()) {
            listInstructionTondeuse.add(getInstruction(instruction));
        }
        return listInstructionTondeuse;
    }
    //récuperer une liste d'enum InstructionTondeuse correspondante à la ligne d'instruction

    public static InstructionTondeuse getInstruction(char cInstruction) {
        for (InstructionTondeuse instructionTondeuse : InstructionTondeuse.values()) {
            if (instructionTondeuse.getCodeInstruction() == cInstruction) {
                return instructionTondeuse;
            }
        }
        return null;
    }

    public static Orientation getOrientation(char cOrientation) {
        for (Orientation orientation : Orientation.values()) {
            if (orientation.getSymbol() == cOrientation) {
                return orientation;
            }
        }
        return null;
    }

}
