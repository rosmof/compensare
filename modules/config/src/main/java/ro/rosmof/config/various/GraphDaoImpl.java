package ro.rosmof.config.various;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GraphDaoImpl implements GraphDao {

    Logger logger = LoggerFactory.getLogger(GraphDaoImpl.class);

    @Override
    public Graph[] getByDate() {
        logger.info("getByDate -> " + this.hashCode());
        return new Graph[0];
    }

    @Override
    public Graph[] getByInterval(int min, int max) {
        return new Graph[0];
    }
}
