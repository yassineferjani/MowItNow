package mowitnow.model;

public enum InstructionTondeuse {
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
