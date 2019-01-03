package ro.rosmof.repository;

import ro.rosmof.model.Identifiable;
import ro.rosmof.model.ModelDatastore;

import java.util.List;

public abstract class AbstractRepository<T extends Identifiable> implements GenericRepository<Long, T> {

    protected ModelDatastore<T> dataStore;

    public AbstractRepository(ModelDatastore<T> dataStore) {
        this.dataStore = dataStore;
    }

    public Long getId(T value) throws Exception {
        return value.getId();
    }

    public List<T> getAllElements() throws Exception {
        return dataStore.getAll();
    }

    public T getElementById(Long id) throws Exception {
        return dataStore.getAll().stream().filter(s -> s.getId().equals(id)).findAny().get();
    }

    public Long save(T element) throws Exception {
        dataStore.add(element);
        return element.getId();
    }
}
