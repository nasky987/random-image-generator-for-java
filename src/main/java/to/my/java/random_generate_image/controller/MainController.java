package to.my.java.random_generate_image.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String getMainView() {
        return "index";
    }

    @GetMapping("/health-check")
    public String healthCheck() {
        return "index";
    }
}
