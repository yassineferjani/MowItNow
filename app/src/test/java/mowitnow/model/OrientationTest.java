package mowitnow.model;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class OrientationTest {

    @ParameterizedTest
    @CsvSource({
            "NORTH,0",
            "SOUTH,180",
            "EAST,90",
            "WEST,270",
    })
    void test_getAngle(Orientation orientation, int expected) {
        assertThat(orientation.getAngle()).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
            "0,NORTH",
            "90,EAST",
            "180,SOUTH",
            "270,WEST",
            "-90,WEST",
            "-180,SOUTH",
            "450,EAST",
            "-450,WEST",

    })
    void test_get(int angle, Orientation expected){
        assertThat(Orientation.get(angle)).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
            "NORTH,90,EAST",
            "NORTH,180,SOUTH",
            "NORTH,270,WEST",
            "NORTH,360,NORTH",
            "SOUTH,-90,EAST",
            "WEST,-180,EAST",
            "EAST,450,SOUTH",
            "SOUTH,360,SOUTH",
    })
    void test_rotate(Orientation orientation,int angle, Orientation expected){
        assertThat(orientation.rotate(angle)).isEqualTo(expected);
    }
}