package com.callsign.imageservice.service;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

@Service
public class ImageServiceImpl implements ImageService {

    private Random random = new Random();

    private static final int DISTORTION_EFFECT = 3;

    private static final String IMAGE_PATH = "images/0.png";

    /**
     * Get the image as stream
     *
     * @return The image as stream
     * @throws IOException The image as stream
     */
    @Override
    public InputStream getImageAsStream() throws IOException {

        ClassPathResource resource = new ClassPathResource(IMAGE_PATH);
        BufferedImage image = ImageIO.read(resource.getInputStream());
        for (int i = 0; i < image.getWidth() / 2; i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                image.setRGB(i, j, getRandomRGB(image.getRGB(i, j)));
            }
        }
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(image, "png", os);
        return new ByteArrayInputStream(os.toByteArray());
    }

    /**
     * Get the random RGB
     *
     * @param pixel - The pixel
     * @return The random RGB
     */
    private int getRandomRGB(int pixel) {
        int alpha = (pixel >> 24) & 0xff;
        int red = (pixel >> 16) & 0xff;
        int green = (pixel >> 8) & 0xff;
        int blue = (pixel) & 0xff;
        return (alpha << 24) | (getRandomPixel(red) << 16) | (getRandomPixel(green) << 8) | (getRandomPixel(blue));
    }

    /**
     * Get random number between the number & number - DISTORTION_EFFECT
     *
     * @param number - The number
     * @return The random number
     */
    private int getRandomPixel(int number) {
        return random.nextInt(DISTORTION_EFFECT) + (number - DISTORTION_EFFECT);
    }
}
