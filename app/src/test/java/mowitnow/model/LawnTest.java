package mowitnow.model;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class LawnTest {
    @ParameterizedTest
    @CsvSource({
            "5, 5, 0, 0, true",
            "5, 5, 0, 5, true",
            "5, 5, 5, 0, true",
            "5, 5, 6, 6, false",
            "1, 1, -1, -1, false"
    })
    void test_isInBound(int maxX, int maxY, int x, int y, boolean expectedResult) {
        Lawn lawn = new Lawn(maxX, maxY);
        Coordinate coordinate = Coordinate.builder()
                .y(y)
                .x(x)
                .build();
        assertThat(lawn.isInBound(coordinate)).isEqualTo(expectedResult);
    }

    @ParameterizedTest
    @CsvSource({
            "5, 5, 0, 0, true",
            "5, 5, 0, 5, true",
            "5, 5, 5, 0, true",
            "5, 5, 6, 6, false"
    })
    void test_isFree(int maxX, int maxY, int x, int y, boolean expectedResult) {
        Lawn lawn = new Lawn(maxX, maxY);
        Coordinate coordinate = Coordinate.builder()
                .y(y)
                .x(x)
                .build();
        assertThat(lawn.isFree(coordinate)).isEqualTo(expectedResult);
    }



}
