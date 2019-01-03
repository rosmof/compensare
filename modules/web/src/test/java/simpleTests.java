import org.junit.Test;
import ro.rosmof.generator.Generator;
import ro.rosmof.model.Splitter;
import ro.rosmof.generator.SplitterGenerator;

import java.util.List;

public class simpleTests {
    @Test
    public void genericTest() {
        Generator<Splitter> g = new Generator<>();
        List<Splitter> list = g.generate(new SplitterGenerator(), 34);

        list.stream().forEach(s -> System.out.println(s.getMessage()));
    }
}
