package ro.rosmof.generator;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
public class Generator<T> {

    boolean isCreated = false;
    List<T> result = null;

    /**
     * The main method that generates random objects of type T with the help of
     * Generable G objects.
     */
    public <G extends Generable<T>> List<T> generate(G generable, int counter) {
        System.out.println("generable id:" + this.hashCode());
        if (!isCreated) {
            result = new ArrayList<>();
            for (int i = 0; i < counter; i++) {
                result.add(generable.singleGenerate());
            }
            isCreated = true;
        }
        return result;
    }

    public List<T> getGeneratedValues() {
        return result;
    }
}
