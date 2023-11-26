package bg.softuni.marketplace.model.dto;

public class FileDownloadDto {

    private String contentType, name;

    private byte[] document;

    public String getContentType() {
        return contentType;
    }

    public FileDownloadDto setContentType(String contentType) {
        this.contentType = contentType;
        return this;
    }

    public String getName() {
        return name;
    }

    public FileDownloadDto setName(String name) {
        this.name = name;
        return this;
    }

    public byte[] getDocument() {
        return document;
    }

    public FileDownloadDto setDocument(byte[] document) {
        this.document = document;
        return this;
    }
}
