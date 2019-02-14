package ro.rosmof.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ro.rosmof.model.Fantasy;
import ro.rosmof.model.Splitter;
import ro.rosmof.repository.FantasyRepository;
import ro.rosmof.repository.SplitterRepository;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private SplitterRepository splitterRepository;

    @Autowired
    private FantasyRepository fantasyRepository;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(HttpServletRequest request, ServletResponse response, Model model) {
        System.out.println("request:" + request.getRequestedSessionId() + " splitterRepository:" + splitterRepository.hashCode() + " controller:" + this.hashCode());
        model.addAttribute("splitterList", splitterRepository.getSplitterList(500, 32));
        return "home_view";
    }

    @RequestMapping(value = "another", method = RequestMethod.GET)
    public String another() {
        return "another";
    }

    @RequestMapping(value = "/fantasy")
    public String fantasy(@RequestParam(value = "counter", defaultValue = "32") int counter,
                          @RequestParam(value = "max", defaultValue = "150") int max,
                          Model model) {
        model.addAttribute("fantasyList", fantasyRepository.getRepositoryList(max, counter));
        return "fantasy_view";
    }

    @RequestMapping(value = "/fantasy/{item}", method = RequestMethod.GET)
    public String fantasyDetail(@PathVariable("item") int id, Model model) {
        Fantasy result = fantasyRepository.getGeneratedList().stream().filter(s -> s.getId() == id).findFirst().get();
        model.addAttribute("item", result);
        return "fantasy_detail_view";
    }

    @RequestMapping(value = "/home/all", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Splitter>> getAllSplitters() {
        return new ResponseEntity<>(splitterRepository.getGeneratedValues(), HttpStatus.OK);
    }
}
