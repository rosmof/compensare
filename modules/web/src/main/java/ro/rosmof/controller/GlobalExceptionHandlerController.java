package ro.rosmof.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ro.rosmof.exception.ItemNotFoundException;
import ro.rosmof.model.ErrorMessage;

@ControllerAdvice

public class GlobalExceptionHandlerController {

    @ExceptionHandler(ItemNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorMessage responseWithError(ItemNotFoundException e) {
        ErrorMessage message = new ErrorMessage();
        message.setCode(HttpStatus.NOT_FOUND.value());
        message.setError("item requested is not found");
        message.setStack(e.getStackTrace().toString());
        return message;
    }
}
