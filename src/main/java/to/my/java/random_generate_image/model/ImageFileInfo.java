package to.my.java.random_generate_image.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.InputStream;

/**
 * Image File Information Model
 */
public class ImageFileInfo {
    private int width;
    private int height;
    private String fileName;
    private ImageContentType contentType;
    private InputStream stream;

    public ImageFileInfo() {

    }

    public ImageFileInfo(int width, int height, String fileName, ImageContentType contentType, InputStream stream) {
        this.width = width;
        this.height = height;
        this.fileName = fileName;
        this.contentType = contentType;
        this.stream = stream;
    }

    public int getWidth() {
        return width;
    }

    public ImageFileInfo setWidth(int width) {
        this.width = width;

        return this;
    }

    public int getHeight() {
        return height;
    }

    public ImageFileInfo setHeight(int height) {
        this.height = height;

        return this;
    }

    public String getFileName() {
        return fileName;
    }

    public ImageFileInfo setFileName(String fileName) {
        this.fileName = fileName;

        return this;
    }

    public ImageContentType getContentType() {
        return contentType;
    }

    public ImageFileInfo setContentType(ImageContentType contentType) {
        this.contentType = contentType;

        return this;
    }

    public InputStream getStream() {
        return stream;
    }

    public ImageFileInfo setStream(InputStream stream) {
        this.stream = stream;

        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
