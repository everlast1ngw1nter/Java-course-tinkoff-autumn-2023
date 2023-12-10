package edu.project4;

import edu.project4.processors.GammaCorrection;
import org.junit.jupiter.api.Test;
import java.awt.Color;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GammaCorrectionTests {

    private void addHitCount(Pixel pixel, int count) {
        for (var i = 0; i < count; i++) {
            pixel.incrementHitCount();
        }
    }

    @Test
    void gammaCorrectionTest() {
        var gammaCor = new GammaCorrection(2);
        var img = FractalImage.create(2, 2);
        var pixel00 = img.getPixel(0, 0);
        pixel00.setColor(new Color(14,14, 41));
        addHitCount(pixel00, 5);
        var pixel10 = img.getPixel(1, 0);
        pixel10.setColor(new Color(7,13, 11));
        addHitCount(pixel10, 11);
        var pixel01 = img.getPixel(0, 1);
        pixel01.setColor(new Color(0,0, 0));
        addHitCount(pixel01, 0);
        var pixel11 = img.getPixel(1, 1);
        pixel11.setColor(new Color(180,180, 180));
        addHitCount(pixel11, 10);
        gammaCor.process(img);
        assertEquals(img.getPixel(0, 0).getColor(), new Color(11, 11, 33));
        assertEquals(img.getPixel(1, 0).getColor(), new Color(7, 13, 11));
        assertEquals(img.getPixel(0, 1).getColor(), new Color(0, 0, 0));
        assertEquals(img.getPixel(1, 1).getColor(), new Color(176, 176, 176));
    }
}
