import org.springframework.stereotype.Controller;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.lang.annotation.Annotation;

public class BaseWebControllerTesting {

    protected MockMvc mvcContext;

    public BaseWebControllerTesting(Object controller) {
        boolean isController = false;
        for (Annotation annotation : controller.getClass().getAnnotations()) {
            if (annotation instanceof Controller) {
                isController = true;
            }
        }

        if (!isController) {
            System.out.println("type is not controller");
            System.exit(-1);
        }

        mvcContext = MockMvcBuilders.standaloneSetup(controller).
                setViewResolvers(new InternalResourceViewResolver("WEB-INF/views", ".jsp")).
                build();
    }


}
