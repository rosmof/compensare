package ro.rosmof.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/custom")
public class CustomServletController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {
        return "/custom/home";
    }
}
