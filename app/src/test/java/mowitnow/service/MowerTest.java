package mowitnow.service;

import mowitnow.model.Coordinate;
import mowitnow.model.Lawn;
import mowitnow.model.Movement;
import mowitnow.model.Orientation;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class MowerTest {

    @ParameterizedTest
    @CsvSource({
            "NORTH,FORWARD,NORTH",
            "NORTH,LEFT,WEST",
            "EAST,RIGHT,SOUTH",
            "SOUTH,LEFT,EAST",
    })
    void test_getNextOrientation(Orientation orientation, Movement movement, Orientation expected){
        assertThat(Mower.getNextOrientation(movement,orientation)).isEqualTo(expected);
    }


    @ParameterizedTest
    @CsvSource({
            "NORTH, 0, 0, 5, 5, FORWARD, 0, 1, NORTH",
            "NORTH, 0, 0, 5, 5, LEFT, 0, 0, WEST",
            "NORTH, 0, 0, 5, 5, RIGHT, 0, 0, EAST",
            "SOUTH, 0, 0, 5, 5, FORWARD, 0, 0, SOUTH",
            "EAST, 5, 5, 5, 5, FORWARD, 5, 5, EAST"
    })
    void test_execute(Orientation orientation, int x, int y, int maxX, int maxY, Movement movement,
                      int expectedX, int expectedY,Orientation expectedOrientation) {
        Lawn lawn = new Lawn(maxX, maxY);
        Mower mower =Mower.builder().currentOrientation(orientation)
                .currentCoordinate(
                Coordinate.builder()
                        .y(y)
                        .x(x)
                        .build())
                .map(lawn)
                .build();

        mower.execute(movement);

        assertThat(mower.currentCoordinate.x()).isEqualTo(expectedX);
        assertThat(mower.currentCoordinate.y()).isEqualTo(expectedY);
        assertThat(mower.getCurrentOrientation()).isEqualTo(expectedOrientation);
    }

}