package goeurotest.utils;

import com.google.common.collect.ImmutableList;
import goeurotest.dto.CsvSuggestionDto;
import goeurotest.exception.ExitException;
import goeurotest.service.SuggestionWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.util.Arrays;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

@Controller
public class AppCommandLineRunner implements CommandLineRunner {

    @Autowired
    private SuggestionWriter csvSuggestionWriter;
    @Autowired
    private GoEuroApiClient goEuroApiClient;
    @Autowired
    private CsvSuggestionConverter csvSuggestionConverter;


    @Override
    public void run(String... args) throws Exception {

        if (args.length != 1) {
            showHelpScreen();
            throw new ExitException(String.format("Program arguments were not correct: %s", Arrays.asList(args)));
        }

        try {
            final String cityName = args[0].trim();
            final String filename = cityName.endsWith(".csv") ? cityName : cityName + ".csv";

            csvSuggestionWriter.write(
                    filename,
                    findSuggestions(cityName));

            System.out.println(String.format("Suggestions have been successfully saved to %s", filename));

        } catch (RuntimeException e) {
            System.err.println("An error's occurred while processing your request. Check the log file for more details");
            throw new ExitException(e);
        }
    }

    private ImmutableList<CsvSuggestionDto> findSuggestions(String cityName) {
        return goEuroApiClient.findSuggestionsByCity(cityName).stream()
                .map(csvSuggestionConverter::toCsvSuggestionDto)
                .collect(collectingAndThen(toList(), ImmutableList::copyOf));
    }

    private void showHelpScreen() {
        System.out.println("Please provide correct parameters!");
        System.out.println("Syntax: java -jar goeuro.jar \"city\"");
        System.out.println("Example: java -jar goeuro.jar berlin");
    }
}
