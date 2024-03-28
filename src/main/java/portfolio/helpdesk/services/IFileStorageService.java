package portfolio.helpdesk.services;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;

public interface IFileStorageService {
    public Path createDirectory(int id, String prefix, Path rootPath) throws IOException;

    public String save(MultipartFile image, Path folder) throws IOException;

    public Resource load(String filename, String directory) throws IOException;
}
