package ro.rosmof.repository;

import ro.rosmof.model.Fantasy;

import java.util.List;

public interface FantasyRepository {
    List<Fantasy> getRepositoryList(int max, int count);
    List<Fantasy> getGeneratedList();
}
