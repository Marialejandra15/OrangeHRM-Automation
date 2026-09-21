package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class csvdatareader {

    public static Object[][] readCsv(String filePath) {

        List<Object[]> data = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            String line;

            // Saltar encabezado
            reader.readLine();

            while ((line = reader.readLine()) != null) {

                String[] values = line.split(",");

                data.add(values);
            }

        } catch (Exception e) {
            throw new RuntimeException("Error al leer el archivo CSV: " + filePath, e);
        }

        return data.toArray(new Object[0][]);
    }
}