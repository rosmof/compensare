package ro.rosmof.model;

import org.junit.Test;

import java.util.function.Predicate;

public class LambdaTests2 {
    @Test
    public void sorting() {

    }


    private Predicate<String> findByContains(String key) {
        return (s) -> s.toLowerCase().contains(key);
    }

    private boolean test(String key) {
        return findByContains(key).test(key);
    }
}
