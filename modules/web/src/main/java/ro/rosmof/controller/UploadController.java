package ro.rosmof.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/upload")
public class UploadController {

    public String upload() {
        System.out.println("called upload controller");
        return null;
    }
}
