package mowitnow.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CheckInputTest {

  @ParameterizedTest
  @CsvSource({
    "5 5,true",
    "55,false",
    "5,false",
    "5  5,false",
    "' 5 5',false",
    "'5 5 ',false",
    "'25 5',true",
  })
  void test_isValidLawerCordinate(String input, boolean expected) {
    assertThat(CheckInput.isValidLawerCordinate(input)).isEqualTo(expected);
  }

  @ParameterizedTest
  @CsvSource({
    "5 5 N,true",
    "55N,false",
    "5 N,false",
    "N 5  5,false",
    "' 5 5 N',false",
    "'5 5 N ',false",
    "'25 55 N',true",
  })
  void test_isValidMowerPosition(String input, boolean expected) {
    assertThat(CheckInput.isValidMowerPosition(input)).isEqualTo(expected);
  }

  @ParameterizedTest
  @CsvSource({
    "'AGGGAGGD',true",
    "A G D,false",
    "'AG5D',false",
    "' AGGGAGGD',false",
    "'AGGGAGGD ',false",
  })
  void test_isValidMowerMovements(String input, boolean expected) {
    assertThat(CheckInput.isValidMowerMovements(input)).isEqualTo(expected);
  }
}
