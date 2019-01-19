package ro.rosmof.model;

import org.apache.commons.lang.RandomStringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class LambdaTests {

    private static final Logger logger = LoggerFactory.getLogger(LambdaTests.class);
    private static final String FILEPATH = "/Users/AlexandruG/Dropbox (ROSMOF)/99. Proiecte/compensare/repository/modules/model/pom.xml";

    /**
     * Created a functional interface BufferedReaderProcessor that is used to manipulate
     * what to read from a BufferedReader. The BufferedReaderProcessor is passed as argument
     * to the function processFile below.
     */
    @Test
    public void various() throws Exception {

        BufferedReaderProcessor processor = (BufferedReader reader) -> reader.readLine() + " - final -";
        String result = processFile((BufferedReader reader) -> reader.readLine() + " -> " + reader.readLine());
        String result2 = processFile(processor);

        logger.info("the final result is: " + result);
        logger.info("the final result 2 is: " + result2);

    }

    private String processFile(BufferedReaderProcessor processor) throws Exception {
        String result = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(FILEPATH))) {
            result = processor.process(reader);
        }

        return result;
    }

    @Test
    public void predicateTest() {

        List<String> payload = new ArrayList<>();

        for (int i = 0; i < 128; i++) {
            payload.add(i % 8 == 0 ? "" : RandomStringUtils.randomAlphabetic(32));
        }

        //filter the list with lambdas
        List<String> filteredList = filter(payload, (String s) -> !s.isEmpty());

        //iterate each item with lambda
        forEach(filteredList, (String s) -> System.out.println(s.startsWith("a") ? "XXXXXXXX" : s));

        //create a map with the length of each string in the list
        Map<String, Integer> lenmap2 = map(filteredList, (String s) -> s.length() + 1);

        logger.info("filtered list size = " + filteredList.size());
    }

    /**
     * Takes a list and a predicate in order to filter the records inside the list.
     */
    private <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        List<T> result = new ArrayList<>();
        list.forEach((T s) -> {
            if (predicate.test(s)) result.add(s);
        });

        return result;
    }

    /**
     * Example on how to use a consumer. The consumer takes a T generic argument
     * and does something. For this reason it may be used to implement a forEach method.
     * <p>
     * The java api also uses the forEach for lists, but this has been done for
     * example purpose.
     */
    private <T> void forEach(List<T> list, Consumer<T> consumer) {
        list.forEach(consumer);
    }

    private <T, R> Map<T, R> map(List<T> initial, Function<T, R> function) {
        Map<T, R> result = new HashMap<>();
        initial.forEach((T t) -> result.put(t, function.apply(t)));
        return result;
    }
}
