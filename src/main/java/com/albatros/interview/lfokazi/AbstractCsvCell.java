package com.albatros.interview.lfokazi;

import java.util.Map;

public abstract class AbstractCsvCell implements CsvCell {

    protected final String rawValue;
    protected boolean valueInitialized = false;
    protected String computedValue;

    protected AbstractCsvCell(final String rawValue) {
        this.rawValue = rawValue;
    }

    protected abstract String computeValue(final Map<String, CsvCell> cellMap);

    @Override
    public String getComputedValue(final Map<String, CsvCell> cellMap) {
        if (!valueInitialized) {
            initValue(cellMap);
        }
        return computedValue;
    }

    private void initValue(final Map<String, CsvCell> cellMap) {
        if (!valueInitialized) {
            computedValue = computeValue(cellMap);
        }

        valueInitialized = true;
    };
}
