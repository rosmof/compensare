package ro.rosmof.repository;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ro.rosmof.generator.Generator;
import ro.rosmof.generator.SplitterGenerator;
import ro.rosmof.model.Splitter;

import java.util.List;

@Repository
public class DefaultSplitterRepositoryImpl implements SplitterRepository, InitializingBean {


    Generator<Splitter> generator;

    @Autowired
    private SplitterGenerator sgen;

    @Autowired
    public DefaultSplitterRepositoryImpl(Generator<Splitter> generator) {
        this.generator = generator;
    }

    /**
     * Returns a random list of splitter objects
     */
    @Override
    public List<Splitter> getSplitterList(int max, int count) {
        return generator.getGeneratedValues();
    }

    @Override
    public List<Splitter> getGeneratedValues() {
        return generator.getGeneratedValues();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        generator.generate(new SplitterGenerator(), 85);
    }
}
