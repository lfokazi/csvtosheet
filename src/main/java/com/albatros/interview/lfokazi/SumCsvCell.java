package com.albatros.interview.lfokazi;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SumCsvCell extends AbstractCsvCell implements CsvCell {

    public SumCsvCell(final String rawValue) {
        super(rawValue);
    }

    @Override
    protected String computeValue(final Map<String, CsvCell> cellMap) {
        final String [] cellParams = rawValue.replace("(", "").replace(")", "").split(" ");
        double sum = 0;
        for (int index = 1; index < cellParams.length; index++) {
            sum = sum + Double.parseDouble(cellMap.get(cellParams[index]).getComputedValue(cellMap));
        }

        return String.valueOf(sum);
    }

    @Override
    public String getOutPutValue(final int maxCellLength) {
        return IntStream.range(computedValue.length(), maxCellLength).mapToObj(i -> " ").collect(Collectors.joining()) + computedValue;
    }
}
