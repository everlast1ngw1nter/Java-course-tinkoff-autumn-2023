package edu.project4;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.IOException;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ImageUtilsTests {

    @Test
    void saveTest(@TempDir Path dir) throws IOException {
        var imgPath = dir.resolve("img.png");
        var img = FractalImage.create(1000, 1000);
        ImageUtils.save(img, imgPath, ImageFormat.PNG);
        assertTrue(imgPath.toFile().exists());
        assertTrue(imgPath.toFile().length() > 1000);
    }
}
