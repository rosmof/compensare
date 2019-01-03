package ro.rosmof.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController {

    @RequestMapping("/badrequest")
    public String showError() {
        return "error";
    }
}
