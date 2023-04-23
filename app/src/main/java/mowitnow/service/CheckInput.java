package mowitnow.service;

public class CheckInput {
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
