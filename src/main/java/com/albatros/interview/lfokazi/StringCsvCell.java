package com.albatros.interview.lfokazi;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StringCsvCell extends AbstractCsvCell implements CsvCell {

    public StringCsvCell(final String rawValue) {
        super(rawValue);
    }

    @Override
    protected String computeValue(final Map<String, CsvCell> cellMap) {
        return rawValue;
    }

    @Override
    public String getOutPutValue(final int maxCellLength) {
        return rawValue + IntStream.range(rawValue.length(), maxCellLength).mapToObj(i -> " ").collect(Collectors.joining());
    }
}
