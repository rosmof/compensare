package ro.rosmof.model;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTests {

    private List<Dish> createDishList() {
        List<Dish> dishList = Arrays.asList(
                new Dish("pork", false, 590, Dish.Type.MEAT),
                new Dish("fish", false, 240, Dish.Type.FISH),
                new Dish("chicken", false, 380, Dish.Type.MEAT),
                new Dish("potato", true, 180, Dish.Type.OTHER),
                new Dish("pizza", true, 740, Dish.Type.OTHER),
                new Dish("tomato", true, 120, Dish.Type.OTHER),
                new Dish("broccoli", true, 90, Dish.Type.OTHER),
                new Dish("tuna", false, 420, Dish.Type.FISH),
                new Dish("lamb", false, 810, Dish.Type.MEAT)
        );

        return dishList;
    }

    @Test
    public void testingStreams() throws Exception {
        List<Dish> dishes = createDishList();

        List<String> firstTwo = dishes.stream()
                .filter(dish -> dish.getType() == Dish.Type.MEAT)
                .limit(2)
                .map(Dish::getName)
                .collect(Collectors.toList());

        List<Integer> dishNameLenghts = dishes.stream().map(Dish::getName).map(String::length).collect(Collectors.toList());
        dishNameLenghts.forEach(System.out::println);


        List<String> filtered = dishes.stream().filter(dish -> dish.getCalories() > 320)
                .map(Dish::getName)
                .limit(3)
                .sorted(String::compareTo)
                .collect(Collectors.toList());


        filtered.forEach(System.out::println);
    }

    /**
     * Extract only the unique characters from an array of words
     */
    @Test
    public void flattenTest() {
        String[] words = {"ghiran", "face", "minuni", "inca", "inu"};
        List<String> unique = Arrays.stream(words).map(s -> s.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());

        unique.forEach(System.out::println);

        List<String[]> uniqueBad = Arrays.stream(words).map(s -> s.split("")).distinct().collect(Collectors.toList());
        uniqueBad.stream().map(Arrays::hashCode).forEach(System.out::println);
    }

    /**
     * Works just fine
     */
    @Test
    public void testSquare() {
        Integer[] values = {1, 2, 3, 4, 5, 6};
        List<Integer> result = Arrays.stream(values).map(this::square).collect(Collectors.toList());
        result.forEach(System.out::println);
    }

    private Integer square(Integer value) {
        return value * value;
    }


    /**
     * Produces the Cartesian result of 2 arrays
     */
    @Test
    public void testPairs() {
        Integer[] list1 = {1, 2, 3, 7};
        Integer[] list2 = {5, 6, 7, 8};

        List<Pair> pairs = Arrays.stream(list1)
                .flatMap(i -> Arrays.stream(list2).map(j -> new Pair(i, j)))
                .filter(CriteriaInterface::criteria)
                .collect(Collectors.toList());

        System.out.println("stop");


        //print if there is there is a 2 multiple in the list
        Arrays.stream(list1).filter(i -> i % 2 == 0).findAny().ifPresent(System.out::println);

        List<Integer[]> totalList = Arrays.asList(list1, list2);
        List<Integer> simplu = Stream.concat(Arrays.stream(list1), Arrays.stream(list2)).distinct().collect(Collectors.toList());
        Integer sum = simplu.stream().reduce(Integer::sum).orElse(0);

        System.out.println("sum = " + sum);


        System.out.println("ceva");
    }


    private class Pair implements CriteriaInterface {
        private Integer x;
        private Integer y;

        public Pair(Integer x, Integer y) {
            this.x = x;
            this.y = y;
        }

        public Integer getX() {
            return x;
        }

        public Integer getY() {
            return y;
        }

        public boolean criteria() {
            return (x + y) % 3 == 0;
        }
    }

    @FunctionalInterface
    interface CriteriaInterface {
        boolean criteria();
    }

    @Test
    public void quiz1() {
        List<Dish> dishes = createDishList();
        Integer result = dishes.stream().map(i -> 1).reduce(0, this::sum);

        System.out.println("total number: " + result);
    }

    private Integer sum(Integer a, Integer b) {
        return a + b;
    }

    @Test
    public void testSomething() {
        List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<Integer> list2 = Collections.emptyList();
        List<Integer> list3 = Arrays.asList(4, 6, 7, 8, 9);
        List<Integer> list4 = Collections.emptyList();
        List<Integer> list5 = Arrays.asList(0, 5, 6, 7);

        List<List<Integer>> toate = Arrays.asList(list1, list2, list3, list4, list5);

        Integer sum = toate.stream().map(List::size).reduce(Integer::sum).orElse(0);



        System.out.println("adad");

    }
}
