package utils;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class ReaderCSVData {
    public static Object[][] getCSVData(String filePath) {
        try (CSVReader csvReader = new CSVReader(new FileReader(filePath))) {
            List<String[]> records = csvReader.readAll();
            // Bỏ qua dòng header
            int rowCount = records.size() - 1;
            Object[][] data = new Object[rowCount][3];

            for (int i = 0; i < rowCount; i++) {
                String[] row = records.get(i + 1);
                data[i][0] = row[0]; // Email
                data[i][1] = row[1]; // Password
                data[i][2] = row[2]; // Test Scenario Type
            }
            return data;
        } catch (IOException | CsvException e) {
            throw new RuntimeException("Can not read file CSV: " + filePath, e);
        }
    }
}
