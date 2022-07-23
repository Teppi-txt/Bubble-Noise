import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {
    static Color lowColor = new Color(47, 34, 53);
    static Color highColor = new Color(255, 254, 242);

    public static void main(String[] args) throws IOException {
        int textureWidth = 700; int textureHeight = 700; int textureDepth = 255;
        //BufferedImage noiseTexture = new BufferedImage(textureWidth, textureHeight, BufferedImage.TYPE_INT_ARGB);
        //NoiseGenerator noise = new VoronoiGenerator(textureWidth, textureHeight, lowColor, highColor);
        //noiseTexture = noise.generate();

        //File exportPath = new File("image.png");
        //ImageIO.write(noiseTexture, "png", exportPath);

        BufferedImage[] noiseStack = new BufferedImage[textureDepth];
        ThreeDimNoiseGenerator noiseTexture =new ThreeDimVoronoiGenerator(textureWidth, textureHeight, textureDepth, lowColor, highColor);
        noiseStack = noiseTexture.generate();

        for (int i = 0; i < textureDepth; i++) {
            File exportPath = new File("image" + String.valueOf(i) + ".png");
            ImageIO.write(noiseStack[i], "png", exportPath);
        }
    }
}