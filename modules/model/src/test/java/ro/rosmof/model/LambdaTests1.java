package ro.rosmof.model;

import org.apache.commons.lang.RandomStringUtils;
import org.junit.Test;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class LambdaTests1 {


    static Map<String, PerformanceTesting> created = new LinkedHashMap<>();

    @Test
    public void operatorNewLambda() {

        //variant 1
        Supplier<PerformanceTesting> spt = PerformanceTesting::new;
        PerformanceTesting pt = spt.get();

        //variant 2, equivalent with lambda
        Supplier<PerformanceTesting> spt2 = () -> new PerformanceTesting();


        //variant 1, 1 argument
        Function<String, PerformanceTesting> function = PerformanceTesting::new;
        PerformanceTesting pt2 = function.apply("ghiran");

        //example on how to use the BiFunction to create objects
        PerformanceTesting performanceTesting = binaryGenerator(PerformanceTesting::new, 23, "ghiran");


        //example on how to use the function with supplier
        PerformanceTesting testing = generator(PerformanceTesting::new);


        PerformanceTesting test = SomePerformanceTests.getPerformanceTest("performanta", "a lu' ghiran");
        System.out.println("adad");
    }


    @Test
    public void lambdaSorting() {

        //create the list
        for (int i = 0; i < 128; i++) {
            getOne(RandomStringUtils.randomAlphabetic(64));
        }

        //try to get a valid key
        String key = "";
        Optional<String> optional = created.keySet().stream().filter(filterByContains("cw")).findAny();
        if (optional.isPresent()) {
            key = optional.get();
            PerformanceTesting current = created.get(key);
        }

        //how about sorting? variant 1
        new ArrayList<>(created.values()).sort(new Comparator<PerformanceTesting>() {
            @Override
            public int compare(PerformanceTesting o1, PerformanceTesting o2) {
                return (o1.testName.toLowerCase().indexOf(13)) - (o2.testName.toLowerCase().indexOf(13));
            }
        });

        //how about sorting? variant 2
        List<PerformanceTesting> values = new ArrayList<>(created.values());
        values.sort(Comparator.comparing((pt) -> pt.testName.toLowerCase().charAt(1)));

        //how about sorting? variant 3
        List<PerformanceTesting> performanceTestingList = new ArrayList<>(created.values());
        performanceTestingList.sort(Comparator.comparing(PerformanceTesting::getCharForComparison));

        System.out.println("adad");

        Function<Integer, Integer> f1 = x -> x + 1;
        Function<Integer, Integer> f2 = x -> x * 3;
        Function<Integer, Integer> f3 = x -> x << 2;

        Function<Integer, Integer> combined = f1.andThen(f2).andThen(f3);
        Integer result = combined.apply(3);
        System.out.println("rezultat = " + result);


        processNumber(4, combined);
    }

    private Predicate<String> filterByContains(String s1) {
        return (String s) -> s.toLowerCase().contains("ce");
    }

    private int processNumber(int number, Function<Integer, Integer> function) {
        return function.apply(number);
    }

    /**
     * Returns a PerformanceTesting object
     */
    private PerformanceTesting getOne(String testName) {
        PerformanceTesting result = generateOne(PerformanceTesting::new, testName);
        created.put(testName, result);

        return result;
    }

    /**
     * Generic function creator for an object
     */
    private <V, T> T generateOne(Function<V, T> function, V value) {
        return function.apply(value);
    }


    /**
     * example on how to create an object based on a Supplier functional interface
     */
    private <T> T generator(Supplier<T> supplier) {
        return supplier.get();
    }

    private <F, S, T> T binaryGenerator(BiFunction<F, S, T> bifunction, F first, S second) {
        return bifunction.apply(first, second);
    }


    /**
     * Very nice example on how to create a map with some keys and equivalent object
     * The getPerformanceTest returns a Test by using Function apply method.
     */
    static class SomePerformanceTests {
        static Map<String, Function<String, PerformanceTesting>> _map = new HashMap<>();

        static {
            _map.put("performanta", PerformanceTesting::new);
            _map.put("securitate", PerformanceTesting::new);
            _map.put("scalabilitate", PerformanceTesting::new);
        }

        public static PerformanceTesting getPerformanceTest(String key, String testName) {
            return _map.get(key).apply(testName);
        }
    }
}
