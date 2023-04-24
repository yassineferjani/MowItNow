package mowitnow.service;

public class CheckInput {
    /** une méthode check retourne void */
    /** les méthodes is... ou has... retourne boolean */
    public static boolean checkFirstLine(String s) {
        return s.matches("\\d+ \\d+");
    }
    public static boolean checkInstructionFirstLine(String str) {
        return str.matches("\\d+ \\d+ [A-Z]");
    }
    public static boolean checkInstructionSecondLine(String str) {
        return str.matches("^\\S+$");
    }

}
