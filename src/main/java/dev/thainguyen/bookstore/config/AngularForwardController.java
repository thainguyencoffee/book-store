package dev.thainguyen.bookstore.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AngularForwardController {

    @GetMapping("{path:^(?!api|public)[^\\.]*}/**")
    public String handleForward() {
        return "forward:/";
    }

}
