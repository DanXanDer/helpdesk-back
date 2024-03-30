package portfolio.helpdesk.services.implementation;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import portfolio.helpdesk.services.IFileStorageService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageServiceImpl implements IFileStorageService {

    private final Path root = Paths.get("src/main/resources/static/tickets");

    @Override
    public Path createDirectory(int id, String prefix, Path rootPath) throws IOException {
        String directoryName = prefix + "-" + id;
        Path directory = rootPath == null ? root.resolve(directoryName) : rootPath.resolve(directoryName);
        Files.createDirectories(directory);
        return directory;
    }

    @Override
    public String save(MultipartFile image, Path folder) throws IOException {
        long timestamp = System.currentTimeMillis();
        String filename = timestamp + "-" + image.getOriginalFilename();
        Files.copy(image.getInputStream(), folder.resolve(filename), StandardCopyOption.REPLACE_EXISTING);
        return filename;
    }

    @Override
    public Resource load(String filename, String directory) throws IOException {
        Path rootReport = root.resolve(directory);
        Path file = rootReport.resolve(filename);
        return new UrlResource(file.toUri());
    }
}
