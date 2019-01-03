package ro.rosmof.repository;

import ro.rosmof.model.Splitter;

import java.util.List;

public interface SplitterRepository {

    List<Splitter> getSplitterList(int max, int count);

    List<Splitter> getGeneratedValues();
}
