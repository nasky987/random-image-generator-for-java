package to.my.java.random_generate_image.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import to.my.java.random_generate_image.service.ImageService;

@Controller
public class MainController {
    private ImageService dummyImageService;

    @Autowired
    public void setDummyImageService(ImageService dummyImageService) {
        this.dummyImageService = dummyImageService;
    }

    @GetMapping("/")
    public String getMainView() {
        return "index";
    }
}
