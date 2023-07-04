package com.albatros.interview.lfokazi;

import java.util.Map;

public interface CsvCell {
    String getComputedValue(final Map<String, CsvCell> cellMap);
    String getOutPutValue(final int maxCellLength);
}
