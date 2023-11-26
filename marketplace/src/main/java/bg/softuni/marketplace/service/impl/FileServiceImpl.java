package bg.softuni.marketplace.service.impl;

import bg.softuni.marketplace.model.domain.FileEntity;
import bg.softuni.marketplace.model.dto.FileDownloadDto;
import bg.softuni.marketplace.repository.FileRepository;
import bg.softuni.marketplace.service.FileService;
import bg.softuni.marketplace.service.exceptions.ObjectNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FileServiceImpl implements FileService {

    private final FileRepository fileRepository;

    public FileServiceImpl(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Override
    public FileEntity upload(MultipartFile img) throws IOException {

        FileEntity newFile = new FileEntity();
        newFile.setFileData(img.getBytes())
                .setContentType(img.getContentType())
                .setFileName(img.getOriginalFilename());
        return this.fileRepository.save(newFile);
    }

    @Override
    public FileDownloadDto download(Long fileId) {
        FileEntity fileEntity = this.fileRepository.findById(fileId).orElseThrow(() -> new ObjectNotFoundException("Image is not found in database!"));
        return new FileDownloadDto()
                .setContentType(fileEntity.getContentType())
                .setName(fileEntity.getFileName())
                .setDocument(fileEntity.getFileData());
    }
}
