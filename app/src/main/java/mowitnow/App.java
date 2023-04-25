/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package mowitnow;

import mowitnow.adapter.Adapter;
import mowitnow.model.Movement;
import mowitnow.service.Mower;
import mowitnow.model.Lawn;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static mowitnow.adapter.Adapter.*;

public class App {
    public static void main(String[] args) {

        try {
            List<String> listLines = Files.readAllLines(
                    Paths.get("D:\\MowItNow\\app\\src\\main\\resources\\fichier.txt"));

            Lawn lawn = Adapter.maptoLawn(listLines.get(0));
            Mower mower1 = maptoMower(listLines.get(1),lawn);
            List<Movement> movementMower1= mapToListOfMovements(listLines.get(2));
            Mower mower2 = maptoMower(listLines.get(3),lawn);
            List<Movement> movementMower2= mapToListOfMovements(listLines.get(4));

            movementMower1.forEach(mower1::execute);
            movementMower2.forEach(mower2::execute);

            printResult(mower1);
            printResult(mower2);



        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

