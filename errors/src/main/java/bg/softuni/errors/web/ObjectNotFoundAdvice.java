package bg.softuni.errors.web;

import bg.softuni.errors.models.exceptions.ObjectNotFoundException;
import bg.softuni.errors.models.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ObjectNotFoundAdvice {

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(ObjectNotFoundException.class)
    public ModelAndView onProductNotFound(ObjectNotFoundException objectNotFoundException){
        ModelAndView modelAndView = new ModelAndView("object-not-found");

        modelAndView.addObject("objectId", objectNotFoundException.getObjectId());
        modelAndView.addObject("objectType", objectNotFoundException.getObjectType());
        return modelAndView;
    }
}
