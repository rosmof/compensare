package ro.rosmof.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ro.rosmof.model.Splitter;
import ro.rosmof.repository.SplitterRepository;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/rest")
public class RestController {

    private final SplitterRepository repository;

    @Autowired
    public RestController(SplitterRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(value = "/one", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Splitter> getFirst(HttpServletRequest request) {
        return new ResponseEntity<>(repository.getGeneratedValues().iterator().next(), HttpStatus.FOUND);
    }
}
