package ro.rosmof.generator;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.stereotype.Component;
import ro.rosmof.model.Fantasy;

import java.util.Calendar;
import java.util.Date;

@Component
public class FantasyGenerator implements Generable<Fantasy> {
    @Override
    public Fantasy singleGenerate() {
        Fantasy fantasy = new Fantasy();
        fantasy.setId(RandomUtils.nextLong() % 7466);
        fantasy.setName(RandomStringUtils.randomAlphanumeric(26));
        fantasy.setStart(new Date(Calendar.getInstance().getTimeInMillis()));
        fantasy.setCounter(RandomUtils.nextInt() % 16);
        return fantasy;
    }
}
