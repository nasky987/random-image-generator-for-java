package to.my.java.random_generate_image.service;

import to.my.java.random_generate_image.model.ImageFileInfo;

import java.io.File;

/**
 * Image Service (Dummy)
 */
public interface ImageService {
    /**
     * generate Dummy Image stored and return Stored File Object
     * @param imageFileInfo
     * @return
     */
    File storedImage(ImageFileInfo imageFileInfo);

    /**
     * generate Dummy Image and return Base64 Sting
     * @param imageFileInfo
     * @return
     */
    String getBase64Image(ImageFileInfo imageFileInfo);
}
