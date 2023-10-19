package bg.softuni.errors.web;

import bg.softuni.errors.models.exceptions.ObjectNotFoundException;
import bg.softuni.errors.models.exceptions.ProductNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserManualController {

    @GetMapping("/manuals/{id}")
    public String getManualsById( @PathVariable("id") Long manualId){
        throw  new ObjectNotFoundException(manualId, "User manual");
    }
}
