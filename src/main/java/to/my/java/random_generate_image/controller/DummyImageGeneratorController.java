package to.my.java.random_generate_image.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import to.my.java.random_generate_image.model.ImageContentType;
import to.my.java.random_generate_image.model.ImageFileInfo;
import to.my.java.random_generate_image.service.ImageService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;

@Controller
public class DummyImageGeneratorController {
    private ImageService DummyImageService;

    @Autowired
    public void setDummyImageService(ImageService dummyImageService) {
        DummyImageService = dummyImageService;
    }

    @GetMapping("/{dimension}")
    @ResponseBody
    public void generateDummyImage(@PathVariable("dimension") String dimension, HttpServletResponse response, Model model) throws IOException {
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

        response.setContentType("image/" + ImageContentType.PNG);

        if (base64Image.startsWith("data:")) {
            base64Image = base64Image.split(",\\s")[1];
        }

        response.getOutputStream().write(Base64.getDecoder().decode(base64Image));
        response.getOutputStream().flush();
    }
}
