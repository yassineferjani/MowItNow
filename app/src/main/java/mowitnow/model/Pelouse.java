package mowitnow.model;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.Data;

@Data
public class Pelouse {

  private final int maxX;

  private final int maxY;

  private final List<Coordinate> coordonnees;

  public Pelouse(final Coordinate coordinate) {

    this.maxX = coordinate.x();

    this.maxY = coordinate.y();

    this.coordonnees = IntStream.rangeClosed(0, coordinate.y())
        .boxed()
        .flatMap(x -> IntStream.rangeClosed(0, coordinate.x())
            .mapToObj(y -> new Coordinate(x, y)))
        .collect(Collectors.toList());
  }

  public boolean isValid(Coordinate coordinate) {
    return coordinate.y() >= 0 && coordinate.y() <= maxY
        && coordinate.x() >= 0 && coordinate.x() <= maxX;
  }
}
