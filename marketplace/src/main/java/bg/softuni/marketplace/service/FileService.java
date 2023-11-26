package bg.softuni.marketplace.service;

import bg.softuni.marketplace.model.domain.FileEntity;
import bg.softuni.marketplace.model.dto.FileDownloadDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public interface FileService {

   FileEntity upload(MultipartFile img) throws IOException;

   FileDownloadDto download(Long fileId);
}
