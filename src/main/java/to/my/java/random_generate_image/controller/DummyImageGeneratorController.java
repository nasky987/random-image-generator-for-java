package to.my.java.random_generate_image.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import to.my.java.random_generate_image.model.ImageContentType;
import to.my.java.random_generate_image.model.ImageFileInfo;
import to.my.java.random_generate_image.service.ImageService;

@Controller
public class DummyImageGeneratorController {
    private ImageService DummyImageService;

    @Autowired
    public void setDummyImageService(ImageService dummyImageService) {
        DummyImageService = dummyImageService;
    }

    @GetMapping("/{dimension}")
    public String generateDummyImage(@PathVariable("dimension") String dimension, Model model) {
        dimension = dimension.trim();

        String base64Image = "";

        if (dimension.matches("\\d+x\\d+")) {
            String[] dimensionValue = dimension.split("x");
            int width = Integer.valueOf(dimensionValue[0]) > 0 ? Integer.valueOf(dimensionValue[0]) : 1;
            int height = Integer.valueOf(dimensionValue[1]) > 0 ? Integer.valueOf(dimensionValue[1]) : 1;

            ImageFileInfo imageFileInfo = new ImageFileInfo();
            imageFileInfo.setWidth(width)
                        .setHeight(height)
                        .setContentType(ImageContentType.PNG);

            base64Image = DummyImageService.getBase64Image(imageFileInfo);
        }

        model.addAttribute("base64Image", base64Image);

        return "dummy-image-service";
    }
}
