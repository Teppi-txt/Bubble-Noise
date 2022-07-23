import java.awt.*;
import java.awt.image.BufferedImage;

public class NoiseGenerator {
    int width;
    int height;

    public NoiseGenerator(int width, int height) {
        this.width = width;
        this.height = height;
    }
    public BufferedImage generate() {
        BufferedImage noise = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                noise.setRGB(i, j, Color.WHITE.getRGB());
            }
        }
        return noise;
    }

    public static Color lerpColor(Color c1, Color c2, double amount) {
        double r = c1.getRed() + ((c2.getRed() - c1.getRed()) * amount);
        double b = c1.getBlue() + ((c2.getBlue() - c1.getBlue()) * amount);
        double g = c1.getGreen() + ((c2.getGreen() - c1.getGreen()) * amount);
        return new Color((int) r, (int) g, (int) b);
    }
}
