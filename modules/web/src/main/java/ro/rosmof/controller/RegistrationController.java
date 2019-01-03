package ro.rosmof.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ro.rosmof.exception.ErrorView;
import ro.rosmof.exception.ItemNotFoundException;
import ro.rosmof.model.Registration;
import ro.rosmof.repository.RegistrationRepository;
import ro.rosmof.validator.RegistrationValidator;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Calendar;

@Controller
@RequestMapping(value = "/registration")
public class RegistrationController {

    @Autowired
    private RegistrationRepository registrationRepository;

    @RequestMapping(method = RequestMethod.GET)
    public String register(Model model) {
        return "registration";
    }

    @Autowired
    private RegistrationValidator regval;

    /**
     * Init binder can be used to customize the WebDataBinder for a parameter argument.
     * In this example we add a validator to registration parameter defined in the
     * signature for 'saveRegistration'. Each time the http request is received, the initBinder
     * is executed.
     */
    @InitBinder("registration")
    protected void initBinderRegistration(WebDataBinder binder) {
        binder.setValidator(regval);
    }

    @ErrorView(value = "/errors/validationexception", status = HttpStatus.GONE)
    @RequestMapping(method = RequestMethod.POST)
    public String saveRegistration(@Valid Registration registration, BindingResult result, ModelMap model) throws Exception {

        if (result.hasErrors()) {
            System.out.println("error while uploading" + result.getAllErrors().get(0));
        } else {
            registration.setRegistrationDate(Calendar.getInstance().getTime());
            registrationRepository.save(registration);
            System.out.println("Registration item saved!");
            model.addAttribute("id", registration.getId());
        }
        return "redirect:/registration/{id}";
    }

    @ErrorView(value = "/errors/validationexception", status = HttpStatus.GONE)
    @RequestMapping(value = "/{registrationId}", method = RequestMethod.GET)
    public String registrationDetails(@PathVariable(value = "registrationId") long id, Model model) {
        try {
            Registration reg = registrationRepository.getElementById(id);
            model.addAttribute("registration", reg);
            return "registration_detail";
        } catch (Exception e) {
            System.out.println("something bad happened inside the get registration details");
        }

        return "redirect:error";
    }

    class AResponse {
        int x;
        String y;
    }

    @RequestMapping(value = "/test", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public AResponse test(HttpServletResponse response) throws Exception {
        AResponse ar = new AResponse();
        ar.x = 13;
        ar.y = "balamuc";

        return ar;
    }
}
