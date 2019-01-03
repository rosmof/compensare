package ro.rosmof.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ro.rosmof.exception.ValidationException;
import ro.rosmof.model.Registration;

@Component("registrationValidator")
public class RegistrationValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Registration.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Registration registration = (Registration) target;

        if (registration.getFile() == null || registration.getFile().isEmpty()) {
            errors.reject("File is not present in uploaded form");
            throw new ValidationException("File is not present in uploaded form");
        }
    }
}
