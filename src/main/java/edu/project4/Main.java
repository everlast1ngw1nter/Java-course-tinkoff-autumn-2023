package edu.project4;

import edu.project4.processors.GammaCorrection;
import edu.project4.render.MultiThreadRenderer;
import edu.project4.render.SingleThreadRenderer;
import edu.project4.transformations.Cross;
import edu.project4.transformations.Exponential;
import edu.project4.transformations.Linear;
import edu.project4.transformations.Sinusoidal;
import edu.project4.transformations.Spherical;
import edu.project4.transformations.Transformation;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        var renderer = new MultiThreadRenderer(new ImageConfig(-1.777, 1.777, -1, 1),  5,8);
        var fractalImage = FractalImage.create(1920, 1080);
        var variations = new ArrayList<Transformation>();
        variations.add(new Cross());
        variations.add(new Exponential());
        variations.add(new Linear());
        variations.add(new Sinusoidal());
        variations.add(new Spherical());
        var res = renderer.render(fractalImage, variations, 100000, 5, 4);
        var corrector = new GammaCorrection(2);
        corrector.process(res);
        ImageUtils.save(res, new File("C:\\Users\\haier\\Desktop\\prog\\1016.jpeg").toPath(), ImageFormat.JPEG);
    }
}
