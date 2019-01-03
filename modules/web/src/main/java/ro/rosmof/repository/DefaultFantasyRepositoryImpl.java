package ro.rosmof.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.rosmof.generator.Generator;
import ro.rosmof.model.Fantasy;
import ro.rosmof.generator.FantasyGenerator;

import java.util.List;

@Component
public class DefaultFantasyRepositoryImpl implements FantasyRepository {

    private Generator<Fantasy> generator;

    @Autowired
    private FantasyGenerator fangen;

    @Autowired
    public DefaultFantasyRepositoryImpl(Generator<Fantasy> generator) {
        this.generator = generator;
    }

    @Override
    public List<Fantasy> getRepositoryList(int max, int count) {
        return generator.generate(fangen, count);
    }

    @Override
    public List<Fantasy> getGeneratedList() {
        return generator.getGeneratedValues();
    }
}
