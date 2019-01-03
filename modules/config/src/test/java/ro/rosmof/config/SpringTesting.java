package ro.rosmof.config;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ro.rosmof.config.various.GraphService2;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/spring/spring.configuration.xml")
public class SpringTesting {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    GraphService2 defaultGraphService;

    @Test
    public void testSpringBeans() {
        for (int i = 0; i < 10; i++) {
            defaultGraphService.getGraphList();
        }
    }

}