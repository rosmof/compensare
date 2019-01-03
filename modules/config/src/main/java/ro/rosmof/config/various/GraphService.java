package ro.rosmof.config.various;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Provider;

public class GraphService {

    static final Logger logger = LoggerFactory.getLogger(GraphService.class);

    public GraphDao getGraphDao() {
        return null;
    }

    public GraphService() {
        logger.info("constructor");
    }



    public Graph[] getGraphList() {
        logger.info("getGraphList");
        //return getGraphDao().getByDate();
        return this.daoProvider.get().getByDate();
    }

    @Autowired
    private Provider<GraphDao> daoProvider;

}
