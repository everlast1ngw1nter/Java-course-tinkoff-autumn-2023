package edu.project4;

import edu.project4.render.SingleThreadRenderer;
import edu.project4.transformations.Cross;
import edu.project4.transformations.Exponential;
import edu.project4.transformations.Transformation;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        var renderer = new SingleThreadRenderer();
        var fractalImage = FractalImage.create(1000, 1000);
        var variations = new ArrayList<Transformation>();
        variations.add(new Cross());
        var res = renderer.render(fractalImage, variations, 100000, (short) 100, 25);
        ImageUtils.save(res, new File("").toPath(), ImageFormat.PNG);
    }
}
