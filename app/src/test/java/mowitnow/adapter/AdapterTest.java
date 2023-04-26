package mowitnow.adapter;

import mowitnow.model.Lawn;
import mowitnow.model.Movement;
import mowitnow.model.Orientation;
import mowitnow.service.Mower;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static mowitnow.model.Movement.*;
import static org.assertj.core.api.Assertions.assertThat;

class AdapterTest {

    @ParameterizedTest
    @CsvSource({
            "D,RIGHT",
            "G,LEFT",
            "A,FORWARD",
    })
    void test_mapToMovement(char movement, Movement expected) {
        assertThat(Adapter.mapToMovement(movement)).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
            "S,SOUTH",
            "N,NORTH",
            "E,EAST",
            "W,WEST",
    })
    void test_mapToOrientation(String input, Orientation expected) {
        assertThat(Adapter.mapToOrientation(input)).isEqualTo(expected);
    }

    static Stream<Arguments> generateData() {
        return Stream.of(
                Arguments.of("AGGAD", List.of(FORWARD, LEFT, LEFT, FORWARD, RIGHT)),
                Arguments.of("D", List.of(RIGHT)),
                Arguments.of("AAAGG", List.of(FORWARD, FORWARD, FORWARD,LEFT,LEFT))
        );
    }
    @ParameterizedTest
    @MethodSource("generateData")
    void testMapToListOfMovements(String input, List<Movement> expected) {
        assertThat(Adapter.mapToListOfMovements(input)).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
            "5 5,5,5",
            "10 5,10,5",
            "5 25,5,25",
            "25 55,25,55",
            "1 1,1,1",
    })
    void test_maptoLawn(String input, int expertedX, int expectedY) {
        Lawn lawn= Adapter.maptoLawn(input);
        assertThat(lawn.maxX).isEqualTo(expertedX);
        assertThat(lawn.maxY).isEqualTo(expectedY);
    }

    @ParameterizedTest
    @CsvSource({
            "5 2 N,5,2,NORTH",
            "5 25 W,5,25,WEST",
            "10 2 N,10,2,NORTH",
            "5 6 S,5,6,SOUTH",
            "50 200 E,50,200,EAST"
    })
    void test_maptoMower(String input, int expectedX, int expectedY, Orientation expectedOrientation) {
        Mower mower = Adapter.maptoMower(input, null);
        assertThat(mower.getCurrentCoordinate().x()).isEqualTo(expectedX);
        assertThat(mower.getCurrentCoordinate().y()).isEqualTo(expectedY);
        assertThat(mower.getCurrentOrientation()).isEqualTo(expectedOrientation);
    }

    @ParameterizedTest
    @CsvSource({
            "NORTH,N",
            "SOUTH,S",
            "WEST,W",
            "EAST,E"
    })
    void test_mapFromOrientation(Orientation input, String expected) {
        assertThat(Adapter.mapFromOrientation(input)).isEqualTo(expected);
    }

}