package goeurotest.utils;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import goeurotest.config.AppProperties;
import goeurotest.domain.Suggestion;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class GoEuroApiClient {

   /* @Value("${application.suggestionUrlTemplate}")
    private String suggestionUrl;*/

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private AppProperties appProperties;

    public List<Suggestion> findSuggestionsByCity(@NonNull String city) {
        ResponseEntity<Suggestion[]> response =
                restTemplate.getForEntity(appProperties.getSuggestionUrlTemplate(), Suggestion[].class, ImmutableMap.of("city", city));

        if (response.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException(String.format("Response status code was %s", response.getStatusCode()));
        }

        return ImmutableList.copyOf(response.getBody());
    }
}
