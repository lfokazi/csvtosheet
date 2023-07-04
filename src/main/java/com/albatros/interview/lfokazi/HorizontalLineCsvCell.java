package com.albatros.interview.lfokazi;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class HorizontalLineCsvCell extends AbstractCsvCell implements CsvCell {

    public HorizontalLineCsvCell(final String rawValue) {
        super(rawValue);
    }

    @Override
    protected String computeValue(final Map<String, CsvCell> cellMap) {
        return "";
    }

    @Override
    public String getOutPutValue(final int maxCellLength) {
        return IntStream.range(0, maxCellLength).mapToObj(i -> "-").collect(Collectors.joining());
    }
}
