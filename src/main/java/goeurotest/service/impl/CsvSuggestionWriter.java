package goeurotest.service.impl;


import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import goeurotest.dto.CsvSuggestionDto;
import goeurotest.service.SuggestionWriter;
import lombok.Cleanup;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Component
public class CsvSuggestionWriter implements SuggestionWriter {

    public static final char SEPARATOR = '|';
    @Value("${application.sortingorder}")
    private List<String> sortingOrder = new ArrayList<>();


    @Override
    public void write(@NonNull String filename, @NonNull List<CsvSuggestionDto> data) {

        try {
            @Cleanup Writer writer = new PrintWriter(new FileWriter(filename), true);
            doWrite(writer, data);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void doWrite(@NonNull Writer writer, @NonNull List<CsvSuggestionDto> data) throws IOException {
        CsvMapper csvMapper = new CsvMapper();
        CsvSchema schema = csvMapper
                .schemaFor(CsvSuggestionDto.class)
                .withHeader()
                .withColumnSeparator(SEPARATOR)
                .sortedBy(sortingOrder.toArray(new String[sortingOrder.size()]));

        csvMapper.writer().with(schema).writeValues(writer).writeAll(data);
    }
}
