package edu.project4;

import edu.project4.render.SingleThreadRenderer;
import edu.project4.transformations.Cross;
import edu.project4.transformations.Exponential;
import edu.project4.transformations.Linear;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class SingleThreadRendererTests {

    @Test
    void singleThreadRendererTests() {
        var rend = new SingleThreadRenderer(new ImageConfig(-1.777, 1.777, -1, 1),  5);
        var canvas = FractalImage.create(30, 30);
        var variations = List.of(new Cross(), new Exponential(), new Linear());
        assertDoesNotThrow(() -> rend.render(canvas, variations, 10000, 10, 5));
    }
}
