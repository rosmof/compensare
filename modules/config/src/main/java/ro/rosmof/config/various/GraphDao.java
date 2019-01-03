package ro.rosmof.config.various;

public interface GraphDao {

    Graph[] getByDate();

    Graph[] getByInterval(int min, int max);
}
