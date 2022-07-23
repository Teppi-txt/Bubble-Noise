import java.awt.*;
import java.awt.image.BufferedImage;

public class ThreeDimNoiseGenerator {
    int width;
    int height;
    int depth;

    public ThreeDimNoiseGenerator(int width, int height, int depth) {
        this.width = width;
        this.height = height;
        this.depth = depth;
    }

    public BufferedImage[] generate() {
        BufferedImage[] noiseStack = new BufferedImage[depth];
        for (int layer = 0; layer < depth; layer++) {
            BufferedImage noise = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            int layerColor = (int)(((float)layer/(float)depth) * 255);
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    noise.setRGB(i, j, new Color(layerColor, layerColor, layerColor).getRGB());
                }
            }
            noiseStack[layer] = noise;
        }
        return noiseStack;
    }

    public static Color lerpColor(Color c1, Color c2, double amount) {
        double r = c1.getRed() + ((c2.getRed() - c1.getRed()) * amount);
        double b = c1.getBlue() + ((c2.getBlue() - c1.getBlue()) * amount);
        double g = c1.getGreen() + ((c2.getGreen() - c1.getGreen()) * amount);
        return new Color((int) r, (int) g, (int) b);
    }
}
