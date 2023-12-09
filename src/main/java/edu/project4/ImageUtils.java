package edu.project4;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;

public final class ImageUtils {
    private ImageUtils() {}

    public static void save(FractalImage fractalImage, Path filename, ImageFormat format) throws IOException {
        int width = fractalImage.width();
        int height = fractalImage.height();

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (var i = 0; i < width; i++) {
            for (var j = 0; j < height; j++) {
                var pixel = fractalImage.pixel(i, j);
                if (pixel.getHitCount() > 0) {
                    image.setRGB(i, j, new Color(255, 255, 255).getRGB());
                } else {
                    image.setRGB(i, j, new Color(0, 0, 0).getRGB());
                }
            }
        }
        ImageIO.write(image, "png", Path.of("C:\\Users\\haier\\Desktop\\prog\\aboba.png").toFile());
    }
}
