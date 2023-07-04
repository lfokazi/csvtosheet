package com.albatros.interview.lfokazi;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class NumberCsvCell extends AbstractCsvCell implements CsvCell {

    public NumberCsvCell(final String rawValue) {
        super(rawValue);
    }

    @Override
    protected String computeValue(final Map<String, CsvCell> cellMap) {
        return rawValue;
    }

    @Override
    public String getOutPutValue(final int maxCellLength) {
        return IntStream.range(computedValue.length(), maxCellLength).mapToObj(i -> " ").collect(Collectors.joining()) + computedValue;
    }
}
