package com.albatros.interview.lfokazi;

import java.util.regex.Pattern;

public class CsvCellFactory {

    private static final Pattern NUMERIC_PATTERN = Pattern.compile("-?\\d+(\\.\\d+)?");

    static CsvCell getCellType(final String rawValue) {
        if (rawValue.startsWith("#(sum")) {
            return new SumCsvCell(rawValue);
        } else if (rawValue.startsWith("#(prod")) {
            return new ProductCsvCell(rawValue);
        } else if (rawValue.startsWith("#hl")) {
            return new HorizontalLineCsvCell(rawValue);
        } else if (NUMERIC_PATTERN.matcher(rawValue).matches()) {
            return new NumberCsvCell(rawValue);
        }

        // default to string
        return new StringCsvCell(rawValue);
    }
}
