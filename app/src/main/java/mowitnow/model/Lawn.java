package mowitnow.model;

import lombok.Data;
import mowitnow.exception.InvalidCoordinate;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
public class Lawn {
    public final int maxX;
    public final int maxY;
    private final Map<Coordinate,Boolean> coordinates;
    public Lawn(int maxX, int maxY) {
        this.maxX = maxX;
        this.maxY = maxY;
        this.coordinates = IntStream.rangeClosed(0,maxY)
                .boxed()
                .flatMap(x -> IntStream.rangeClosed(0, maxX)
                        .mapToObj(y -> new Coordinate(x, y)))
                .collect(Collectors.toMap(Function.identity(), c -> true));
    }

    public boolean isInBound(Coordinate coordinate) {
        return coordinate.y()>= 0 && coordinate.y() <= maxY
                && coordinate.x() >= 0 && coordinate.x() <= maxX;
    }

    public boolean isFree(Coordinate coordinate){
        return coordinates.getOrDefault(coordinate, false);
    }
    public void block(Coordinate coordinate){changeStateCoordinate(coordinate,false);}
    public void free(Coordinate coordinate){changeStateCoordinate(coordinate,true);}


    private void changeStateCoordinate(Coordinate coordinate , boolean b){
        if (coordinates.containsKey(coordinate))
            coordinates.put(coordinate,b);
        else
            throw new InvalidCoordinate("Invalid coordiante : "+ coordinate);
    }
}
