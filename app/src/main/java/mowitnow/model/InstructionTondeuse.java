package mowitnow.model;

public enum InstructionTondeuse {

    /** les char D, G , A appartiennent au monde extérieur. Donc ne doit pas être avec un enum du domain */
    Right('D'),
    Left('G'),
    Forward('A');
    private final char codeInstruction;
    private InstructionTondeuse(final char codeInstruction) {
        this.codeInstruction = codeInstruction;
    }
    public char getCodeInstruction() {
        return codeInstruction;
    }

}
