package mowitnow.model;

import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
public class Pelouse {
    private final List<Coordinate> coordonnees;
    public Pelouse(final Coordinate coordinate){
        this.coordonnees = IntStream.rangeClosed(0, coordinate.y())
                .boxed()
                .flatMap(x -> IntStream.rangeClosed(0, coordinate.x())
                        .mapToObj(y -> new Coordinate(x, y)))
                .collect(Collectors.toList());
    }
}
