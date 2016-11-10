package goeurotest.service;

import goeurotest.dto.CsvSuggestionDto;
import lombok.NonNull;

import java.util.List;

public interface SuggestionWriter {
    void write(@NonNull String filename, @NonNull List<CsvSuggestionDto> data);
}
