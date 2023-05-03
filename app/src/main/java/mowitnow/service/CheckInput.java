package mowitnow.service;

public class CheckInput {
  public static boolean isValidLawerCordinate(String s) {
    return s.matches("\\d+ \\d+");
  }

  public static boolean isValidMowerPosition(String str) {
    return str.matches("\\d+ \\d+ [A-Z]+");
  }

  public static boolean isValidMowerMovements(String str) {
    return str.matches("^[\\p{L}]+$");
  }
}
