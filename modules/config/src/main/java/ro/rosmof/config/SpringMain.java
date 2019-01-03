package ro.rosmof.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ro.rosmof.config.various.Graph;
import ro.rosmof.config.various.GraphService;
import ro.rosmof.config.various.GraphService2;


public class SpringMain {

    final static Logger logger = LoggerFactory.getLogger(SpringMain.class);

    @Autowired
    GraphService defaultGraphService;

    @Autowired
    Graph graph;

    public static void main(String[] args) {
        System.out.println("starting");
        ApplicationContext context = new ClassPathXmlApplicationContext("/spring/spring.configuration.xml");
//        SpringMain sm = context.getBean(SpringMain.class);

        SpringMain sm = new SpringMain();

        AutowireCapableBeanFactory factory = context.getAutowireCapableBeanFactory();
        System.out.println(sm.graph);
        System.out.println("balamuc " + sm.defaultGraphService);
        factory.autowireBean(sm);



        System.out.println(sm.graph);
        System.out.println("balamuc " + sm.defaultGraphService);
    }

    public void execute(String[] args) {

    }

    @Autowired
    public SpringMain() {
        for (int i = 0; i < 5; i++) {
//            xxx.getGraphList();
        }
    }


}
