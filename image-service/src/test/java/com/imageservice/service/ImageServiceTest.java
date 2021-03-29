package com.imageservice.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class ImageServiceTest {

    private ImageService imageService;

    private ClassPathResource resource;

    @BeforeEach
    public void beforeEach() {
        imageService = new ImageServiceImpl();
        resource = new ClassPathResource("images/0.jpg");
    }

    @Test
    void testGetImageAsStream() throws IOException {
        InputStream imageAsStream = imageService.getImageAsStream();
        assertNotNull(imageAsStream);
    }

    @Test
    void whenGetImageAsStreamCalled_thenExpectLeftPartChanged() throws IOException {
        InputStream imageAsStream = imageService.getImageAsStream();
        assertNotNull(imageAsStream);

        BufferedImage image = ImageIO.read(imageAsStream);
        BufferedImage originalImage = ImageIO.read(resource.getInputStream());
        boolean isLeftPartChanged = false;
        for (int i = 0; i < image.getWidth() / 2; i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                isLeftPartChanged = (image.getRGB(i, j) != originalImage.getRGB(i, j));
                if (isLeftPartChanged) {
                    break;
                }
            }
        }
        assertTrue(isLeftPartChanged);
    }

    @Test
    void whenGetImageAsStreamCalled_thenExpectRightPartUnchanged() throws IOException {
        InputStream imageAsStream = imageService.getImageAsStream();
        assertNotNull(imageAsStream);

        BufferedImage image = ImageIO.read(imageAsStream);
        BufferedImage originalImage = ImageIO.read(resource.getInputStream());
        boolean isRightPartChanged = false;
        for (int i = image.getWidth() / 2; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                isRightPartChanged = (image.getRGB(i, j) != originalImage.getRGB(i, j));
                if (isRightPartChanged) {
                    break;
                }
            }
        }
        assertFalse(isRightPartChanged);
    }

}