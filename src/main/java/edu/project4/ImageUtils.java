package edu.project4;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.nio.file.Path;
import javax.imageio.ImageIO;

public final class ImageUtils {
    private ImageUtils() {}

    public static RenderedImage create(FractalImage fractalImage) {
        var image = new BufferedImage(fractalImage.width(), fractalImage.height(), BufferedImage.TYPE_INT_RGB);
        for (var i = 0; i < fractalImage.width(); i++) {
            for (var j = 0; j < fractalImage.height(); j++) {
                var pixel = fractalImage.pixel(i, j);
                var color = pixel.getColor();
                if (color == null) {
                    color = new Color(0, 0, 0);
                }
                image.setRGB(i, j, color.getRGB());
            }
        }
        return image;
    }

    public static void save(FractalImage fractalImage, Path filename, ImageFormat format)
            throws IOException {
        var image = create(fractalImage);
        ImageIO.write(image, format.toString().toLowerCase(), filename.toFile());
    }
}
