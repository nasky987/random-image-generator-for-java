package to.my.java.random_generate_image.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import to.my.java.random_generate_image.model.ImageFileInfo;
import to.my.java.random_generate_image.service.ImageService;
import to.my.java.random_generate_image.util.DummyImageGenerator;

import java.io.File;

@Service
public class DummyImageServiceImpl implements ImageService {
    private DummyImageGenerator dummyImageGenerator;

    @Autowired
    public void setDummyImageGenerator(DummyImageGenerator dummyImageGenerator) {
        this.dummyImageGenerator = dummyImageGenerator;
    }

    @Override
    public File storedImage(ImageFileInfo imageFileInfo) {
        return dummyImageGenerator.generateDummyImage(imageFileInfo);
    }

    public String getBase64Image(ImageFileInfo imageFileInfo) {
        return dummyImageGenerator.generateDummyImageWithoutStored(imageFileInfo);
    }
}
