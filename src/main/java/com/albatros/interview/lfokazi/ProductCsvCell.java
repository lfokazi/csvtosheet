package com.albatros.interview.lfokazi;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ProductCsvCell extends AbstractCsvCell implements CsvCell {

    public ProductCsvCell(final String rawValue) {
        super(rawValue);
    }

    @Override
    protected String computeValue(final Map<String, CsvCell> cellMap) {
        final String [] cellParams = rawValue.replace("(", "").replace(")", "").split(" ");
        double product = 1;
        for (int index = 1; index < cellParams.length; index++) {
            product = product * Double.parseDouble(cellMap.get(cellParams[index]).getComputedValue(cellMap));
        }

        return String.valueOf(product);
    }

    @Override
    public String getOutPutValue(final int maxCellLength) {
        return IntStream.range(computedValue.length(), maxCellLength).mapToObj(i -> " ").collect(Collectors.joining()) + computedValue;
    }
}
