package ro.rosmof.exception;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class RosmofHandlerExceptionResolver implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        ModelAndView result = null;
        ErrorView errorView;

        if (handler instanceof HandlerMethod) {
            HandlerMethod method = (HandlerMethod) handler;
            if ((errorView = method.getMethodAnnotation(ErrorView.class)) != null) {
                result = new ModelAndView(errorView.value());
                result.addObject(ExceptionModelAttribute.REQUEST_URI.toString(), request.getRequestURI());
                result.addObject(ExceptionModelAttribute.SESSION_ID.toString(), request.getRequestedSessionId());
                result.addObject(ExceptionModelAttribute.EXCEPTION.toString(), ex.getMessage());
                result.addObject(ExceptionModelAttribute.EXCEPTION_STACK.toString(), ex.getStackTrace());
                result.addObject(ExceptionModelAttribute.STATUS_VALUE.toString(), errorView.status().value());
                result.addObject(ExceptionModelAttribute.STATUS_REASON.toString(), errorView.status().getReasonPhrase());

                response.setStatus(errorView.status().value());
            }
        }

        return result;
    }
}
