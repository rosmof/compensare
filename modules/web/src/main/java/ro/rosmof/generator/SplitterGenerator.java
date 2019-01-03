package ro.rosmof.generator;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import ro.rosmof.model.Splitter;

import java.sql.Date;
import java.util.Calendar;

@Component
public class SplitterGenerator implements Generable<Splitter> {
    @Override
    public Splitter singleGenerate() {
        Splitter result = new Splitter(RandomStringUtils.randomAlphanumeric(14), new Date(Calendar.getInstance().getTimeInMillis()));
        result.setId(RandomUtils.nextLong() % 18999);
        result.setLatitude(RandomUtils.nextDouble());
        result.setLongitude(RandomUtils.nextDouble());
        return result;
    }
}
