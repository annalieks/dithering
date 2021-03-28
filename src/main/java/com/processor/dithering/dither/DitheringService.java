package com.processor.dithering.dither;

import com.processor.dithering.exception.ProcessingException;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class DitheringService {

    @Value("${media.folder}")
    private String resourcesFolder;

    public void processImage(MultipartFile file) {
        BufferedImage image;
        try {
            image = ImageIO.read(new ByteArrayInputStream(file.getBytes()));
            Dither.process(image);
        } catch (IOException e) {
            throw new ProcessingException("Could not convert image into bytes");
        }

        String fileName = file.getOriginalFilename();
        try {
            saveResult(image, fileName);
        } catch (IOException e) {
            throw new ProcessingException("Could not save the processed image");
        }
    }

    private void saveResult(BufferedImage image, String name) throws IOException {
        Path dir = Path.of(resourcesFolder);
        Files.createDirectories(dir);

        Path imagePath = dir.resolve(name);
        File file = new File(imagePath.toString());

        ImageIO.write(image, FilenameUtils.getExtension(name), file);
    }

}
