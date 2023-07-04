package com.albatros.interview.lfokazi;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class CsvToSheetApp {

    static final String USER_DIR = System.getProperty("user.dir");
    private static final int CAPITAL_A_ASCII = 'A';

    public static void main(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("No CSV file name provided! Please provide a valid file name.");
        }

        final String fileName = args[0];
        if (Objects.isNull(fileName) || fileName.isEmpty()) {
            throw new IllegalArgumentException("No CSV file name provided! Please provide a valid file name.");
        }

        System.out.println(getStringFormattedSheet(fileName));
    }

    static String getStringFormattedSheet(final String fileName) {
        final List<String> fileLines;
        try {
            fileLines = readALinesFromCsvFile(fileName);
        } catch (IOException e) {
            throw new IllegalArgumentException("Specified file with name " + fileName + " not found!");
        }

        if (fileLines.isEmpty()) {
            return "";
        }

        final int numOfRows = fileLines.size();
        final Map<String, CsvCell> cellMap = new HashMap<>();
        int rowIndex = 1;
        final List<List<CsvCell>> rows = new ArrayList<>(numOfRows);
        for (final String fileLine : fileLines) {
            int asciiIndex = CAPITAL_A_ASCII;
            final List<CsvCell> rowCells = new ArrayList<>();
            final List<String> cellValues = Arrays.stream(fileLine.split(",")).collect(Collectors.toList());
            for (final String cellValue : cellValues) {
                final CsvCell csvCell = CsvCellFactory.getCellType(cellValue);
                cellMap.put((char) asciiIndex + "" + rowIndex, csvCell);
                rowCells.add(csvCell);
                asciiIndex++;
            }

            rows.add(rowCells);
            rowIndex++;
        }

        // compute the values
        final int maxCellLength = cellMap.values().stream()
                .max(Comparator.comparingInt(cell -> cell.getComputedValue(cellMap).length()))
                .map(c -> c.getComputedValue(cellMap).length()).orElse(0);

        // print out the data
        final StringBuilder outputBuilder = new StringBuilder();
        for (int i = 0; i < numOfRows; i++) {
            final List<CsvCell> rowCells = rows.get(i);
            for (final CsvCell cell : rowCells) {
                final String cellValue = cell.getOutPutValue(maxCellLength);
                outputBuilder.append(cellValue).append("|");
            }

            if (i + 1 < numOfRows) {
                outputBuilder.append("\n");
            }
        }

        return outputBuilder.toString();
    }

    private static List<String> readALinesFromCsvFile(final String csvFileName) throws IOException {
        final Path csvFilePath = Paths.get(USER_DIR, csvFileName);
        return Files.readAllLines(csvFilePath);
    }

}
