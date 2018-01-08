package to.my.java.random_generate_image.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import to.my.java.random_generate_image.model.ImageFileInfo;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.Random;

/**
 * Dummy Image Generator
 */
@Component
public class DummyImageGenerator {
    private static final Logger LOGGER = LoggerFactory.getLogger(DummyImageGenerator.class);
    private static final String FILE_FORMAT = "%s%s%s.%s";
    private static final String BASE64_IMAGE_FORMAT = "data:image/%s;base64, %s";

    private String absoluteStoredPath;

    @Value("${storage.absolute-stored-path}")
    public void setAbsoluteStoredPath(String absoluteStoredPath) {
        this.absoluteStoredPath = absoluteStoredPath;
    }

    public File generateDummyImage(final ImageFileInfo imageFileInfo) {
        final String displayDummyImageName = String.format("%s x %s", imageFileInfo.getWidth(), imageFileInfo.getHeight());
        final String imageFileExtension = imageFileInfo.getContentType().getExtension();

        final BufferedImage image = new BufferedImage(imageFileInfo.getWidth(), imageFileInfo.getHeight(), BufferedImage.TYPE_INT_RGB); //None-Alpha
        final Graphics2D graphics = image.createGraphics();
        final Color color = generateDummyImageColorPixel();

        graphics.setPaint(color);
        graphics.fillRect(0, 0, image.getWidth(), image.getHeight());

        graphics.setColor(Color.BLACK);

        if (Color.BLACK.getRGB() == color.getRGB()) {
            graphics.setColor(Color.WHITE);
        }

        graphics.setFont(new Font("Arial Black", Font.BOLD, 20));

        final int textWidth = (int) graphics.getFontMetrics().getStringBounds(displayDummyImageName, graphics).getWidth();
//        final int textHeight = (int) graphics.getFontMetrics().getStringBounds(displayDummyImageName, graphics).getHeight();

        final int positionX = (imageFileInfo.getWidth() > textWidth) ? (imageFileInfo.getWidth()/2) - (textWidth/2) : (imageFileInfo.getWidth()/2);
//        final int positionY = (imageFileInfo.getHeight()/2) - (textHeight/2);
        final int positionY = imageFileInfo.getHeight()/2;

        graphics.drawString(displayDummyImageName, positionX, positionY);

        final File file = new File(String.format(FILE_FORMAT, absoluteStoredPath, File.separator, imageFileInfo.getFileName(), imageFileExtension));

        try {
            ImageIO.write(image, imageFileExtension, file);
        } catch (IOException exception) {
            LOGGER.error("error", exception);
        }

        return file;
    }

    public String generateDummyImageWithoutStored(final ImageFileInfo imageFileInfo) {
        final String displayDummyImageName = String.format("%s x %s", imageFileInfo.getWidth(), imageFileInfo.getHeight());
        final String imageFileExtension = imageFileInfo.getContentType().getExtension();

        final BufferedImage image = new BufferedImage(imageFileInfo.getWidth(), imageFileInfo.getHeight(), BufferedImage.TYPE_INT_RGB); //None-Alpha
        final Graphics2D graphics = image.createGraphics();
        final Color color = generateDummyImageColorPixel();

        graphics.setPaint(color);
        graphics.fillRect(0, 0, image.getWidth(), image.getHeight());

        graphics.setColor(Color.BLACK);

        if (Color.BLACK.getRGB() == color.getRGB()) {
            graphics.setColor(Color.WHITE);
        }

        graphics.setFont(new Font("Arial Black", Font.BOLD, 20));

        final int textWidth = (int) graphics.getFontMetrics().getStringBounds(displayDummyImageName, graphics).getWidth();
//        final int textHeight = (int) graphics.getFontMetrics().getStringBounds(displayDummyImageName, graphics).getHeight();

        final int positionX = (imageFileInfo.getWidth() > textWidth) ? (imageFileInfo.getWidth()/2) - (textWidth/2) : (imageFileInfo.getWidth()/2);
//        final int positionY = (imageFileInfo.getHeight()/2) - (textHeight/2);
        final int positionY = imageFileInfo.getHeight()/2;

        graphics.drawString(displayDummyImageName, positionX, positionY);

        try(ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            ImageIO.write(image, imageFileExtension, byteArrayOutputStream);
            byteArrayOutputStream.flush();

            return String.format(BASE64_IMAGE_FORMAT, imageFileExtension, Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray()));
        } catch (IOException exception) {
            LOGGER.error("error", exception);
        }

        return "";
    }

    private Color generateDummyImageColorPixel() {
        Random random = new Random();

        return new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
    }

    /**
     * Old Version
     * @param imageFileInfo
     * @return
     */
    @Deprecated
    public File generateDummyAlphaImage(final ImageFileInfo imageFileInfo) {
        final BufferedImage image = new BufferedImage(imageFileInfo.getWidth(), imageFileInfo.getHeight(), BufferedImage.TYPE_INT_ARGB);

        int pixel = generateDummyImageAlphaColorPixel();

        for (int y = 0; y < imageFileInfo.getHeight(); y++) {
            for (int x = 0; x < imageFileInfo.getWidth(); x++) {
                image.setRGB(x, y, pixel);
            }
        }

        File file = new File(String.format(FILE_FORMAT, absoluteStoredPath, File.separator, imageFileInfo.getFileName(), imageFileInfo.getContentType().getExtension()));

        try {
            ImageIO.write(image, imageFileInfo.getContentType().getExtension(), file);
        } catch (IOException exception) {
            LOGGER.error("error", exception);
        }

        return file;
    }

    /**
     * Old Version
     * @return
     */
    @Deprecated
    private int generateDummyImageAlphaColorPixel() {
        Random random = new Random();

        int alpha = random.nextInt(255);
        int red = random.nextInt(255);
        int green = random.nextInt(255);
        int blue = random.nextInt(255);

        return (alpha << 24) | (red << 16) | (green << 8) | blue;
    }
}
