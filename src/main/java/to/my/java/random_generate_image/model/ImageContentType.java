package to.my.java.random_generate_image.model;

/**
 * Image Content Type Enum
 */
public enum ImageContentType {
    JPG("jpg"),
    PNG("png"),
    GIF("gif");

    private String extension;

    ImageContentType(String extension) {
        this.extension = extension;
    }

    public String getExtension() {
        return extension;
    }
}
