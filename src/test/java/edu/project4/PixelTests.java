package edu.project4;

import org.junit.jupiter.api.Test;
import java.awt.Color;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PixelTests {

    @Test
    void nullBeforeSetColorTest() {
        var pixel = new Pixel();
        assertNull(pixel.getColor());
    }

    @Test
    void setColorTest() {
        var pixel = new Pixel();
        var color = new Color(119, 33, 33);
        pixel.setColor(color);
        assertEquals(pixel.getColor(), color);
    }

    @Test
    void incrementHitCountTest() {
        var pixel = new Pixel();
        for (var i = 0; i < 10; i++) {
            pixel.incrementHitCount();
        }
        assertEquals(pixel.getHitCount(), 10);
    }
}
