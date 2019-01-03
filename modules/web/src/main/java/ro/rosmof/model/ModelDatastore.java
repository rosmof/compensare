package ro.rosmof.model;

import org.apache.commons.lang.math.RandomUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
public class ModelDatastore<T extends Identifiable> {
    private List<T> ds;

    public ModelDatastore() {
        ds = new ArrayList<>();
    }

    public void add(T element) {
        if (element.getId() == null || element.getId() == 0) {
            element.setId(RandomUtils.nextLong() % 59866);
        }
        ds.add(element);
    }

    public int size() {
        return ds.size();
    }

    public List<T> getAll() {
        return ds;
    }

    public T getById(long id) {
        return ds.stream().filter(w -> w.getId() == id).findAny().get();
    }
}
