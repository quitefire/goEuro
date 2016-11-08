package goeurotest.utils;

import goeurotest.domain.Suggestion;
import goeurotest.dto.CsvSuggestionDto;
import lombok.NonNull;
import org.springframework.stereotype.Component;

@Component
public class CsvSuggestionConverter {
    public CsvSuggestionDto toCsvSuggestionDto(@NonNull Suggestion input) {
        return new CsvSuggestionDto().builder()
                .id(input.getId())
                .name(input.getName())
                .type(input.getType())
                .latitude(input.getGeoPosition().getLatitude())
                .longitude(input.getGeoPosition().getLongitude())
                .build();
    }
}
