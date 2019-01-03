import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ro.rosmof.controller.HomeController;

public class WebControllerTesting extends BaseWebControllerTesting {

    public WebControllerTesting() {
        super(new HomeController());
    }



    @Test
    public void simpleControllerTest() throws Exception {

        mvcContext.perform(MockMvcRequestBuilders.get("/home"))
                .andExpect(MockMvcResultMatchers.view().name("home_view"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("splitterList"))
                .andExpect(MockMvcResultMatchers.model().size(1));

        for (int i = 0; i < 10; i++) {
            mvcContext.perform(MockMvcRequestBuilders.get("/home"));
        }
    }


    @Test
    public void fantasyControllerTest() throws Exception {
        mvcContext.perform(MockMvcRequestBuilders.get("/fantasy"))
                .andExpect(MockMvcResultMatchers.view().name("fantasy_view"))
                .andExpect(MockMvcResultMatchers.model().attribute("fantasyList", IsCollectionWithSize.hasSize(24)));
    }
}
