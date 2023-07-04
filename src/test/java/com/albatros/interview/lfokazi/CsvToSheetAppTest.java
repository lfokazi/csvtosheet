package com.albatros.interview.lfokazi;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CsvToSheetAppTest {

    private static final String DATA_DIR_RELATIVE_PATH = "src/test/java/com/albatros/interview/lfokazi";

    @Test
    public void main_file_name_missing() {
        final Exception iae = assertThrows(IllegalArgumentException.class, () -> CsvToSheetApp.main(new String[0]));
        assertThat(iae.getMessage(), containsString("No CSV file name provided"));
    }

    @Test
    public void getStringFormattedSheet_empty_file() {
        final String output = CsvToSheetApp.getStringFormattedSheet(getRelativePathFileName("csv_to_sheet_empty_file.csv"));
        assertThat(output.length(), is(0));
    }

    @Test
    public void getStringFormattedSheet_happy_case() throws IOException {
        final String output = CsvToSheetApp.getStringFormattedSheet(getRelativePathFileName("csv_to_sheet_happy_case.csv"));
        final String expected = getExpectedResultOuputFileContent(getRelativePathFileName("csv_to_sheet_happy_case_result.csv"));

        assertThat(output, is(expected));
    }

    @Test
    public void getStringFormattedSheet_happy_case_unordered() throws IOException {
        final String output = CsvToSheetApp.getStringFormattedSheet(getRelativePathFileName("csv_to_sheet_happy_case_unordered.csv"));

        final String expected = getExpectedResultOuputFileContent(getRelativePathFileName("csv_to_sheet_happy_case_unordered_result.csv"));

        assertThat(output, is(expected));
    }

    private String getExpectedResultOuputFileContent(final String resultFileName) throws IOException {
        final Path csvFilePath = Paths.get(CsvToSheetApp.USER_DIR, resultFileName);
        return String.join("\n", Files.readAllLines(csvFilePath));
    }

    private String getRelativePathFileName(final String fileName) {
        return DATA_DIR_RELATIVE_PATH + "/" + fileName;
    }
}
