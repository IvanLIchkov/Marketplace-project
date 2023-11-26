package bg.softuni.marketplace.model.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "files")
public class FileEntity extends BaseEntity{

    @Column
    private String fileName;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(length = Integer.MAX_VALUE)
    private byte[] fileData;

    @Column
    private String contentType;

    public String getFileName() {
        return fileName;
    }

    public FileEntity setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public byte[] getFileData() {
        return fileData;
    }

    public FileEntity setFileData(byte[] fileData) {
        this.fileData = fileData;
        return this;
    }

    public String getContentType() {
        return contentType;
    }

    public FileEntity setContentType(String contentType) {
        this.contentType = contentType;
        return this;
    }
}
