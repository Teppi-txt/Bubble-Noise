import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ThreeDimVoronoiGenerator extends ThreeDimNoiseGenerator{
    float cellsPerRow = 10F;
    float lerpFactor = 0F;
    float cellWidth;
    float cellHeight;
    Color lowColor;
    Color highColor;

    ArrayList<WorldCell> cells = new ArrayList<WorldCell>();

    public ThreeDimVoronoiGenerator(int textureWidth, int textureHeight, int textureDepth, Color lowColor, Color highColor) {
        super(textureWidth, textureHeight, textureDepth);
        this.cellWidth = textureWidth/cellsPerRow;
        this.cellHeight = textureHeight/cellsPerRow;
        this.lowColor = lowColor;
        this.highColor = highColor;
    }

    public BufferedImage[] generate() {
        BufferedImage[] noiseStack = new BufferedImage[depth];
        for (int row = -1; row <= cellsPerRow; row++) {
            for (int column = -1; column <= cellsPerRow; column++) {
                //this line of math generates a random point between teh bounds of the call
                WorldPoint randomPoint = new WorldPoint((float)(row + Math.random()) * cellWidth, (float)(column + Math.random()) * cellHeight, (float)(Math.random()) * depth);

                //creates a cell containing said random point
                WorldCell cell = new WorldCell(row , column , cellWidth, cellHeight, randomPoint);

                //adds the cell to the list of cells to keep track
                cells.add(cell);
            }
        }
        VoronoiStackAlgorithm(noiseStack);
        return noiseStack;
    }

    public void VoronoiStackAlgorithm(BufferedImage[] noiseStack) {
        for (int i = 0; i < depth; i++) {
            BufferedImage noise = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            float maximumLength = new WorldPoint(0, 0, 0).distanceTo(new WorldPoint(cellWidth, cellHeight, (int)(depth/1.5)));

            for (int row = 0; row < width; row++) {
                for (int col = 0; col < width; col++) {
                    WorldPoint pixelAsPoint = new WorldPoint(row, col, i);
                    Point pixelCellIndex = pixelAsPoint.getCell(cellWidth, cellHeight);
                    float shortestDistance = Float.MAX_VALUE;
                    ArrayList<WorldCell> cellsToCheck = new ArrayList<WorldCell>();

                    for (WorldCell cell:cells) {
                        if (Math.abs(cell.x - pixelCellIndex.x) <= 5 && Math.abs(cell.y - pixelCellIndex.y) <= 5) {
                            //adds it to the cellsToCheck list
                            cellsToCheck.add(cell);
                        }
                    }

                    for (WorldCell cell : cellsToCheck) {
                        //stores the distance from the pixel to the current point
                        float distance = pixelAsPoint.distanceTo(cell.point);
                        float wraparoundDistance = Math.min(pixelAsPoint.distanceTo(cell.point.shiftZ(depth)), pixelAsPoint.distanceTo(cell.point.shiftZ(-depth)));
                        if (distance < shortestDistance) shortestDistance = distance;
                        if (wraparoundDistance < shortestDistance) shortestDistance = wraparoundDistance;
                    }

                    float pixelBrightness = Math.min(shortestDistance/maximumLength + lerpFactor, 1);
                    noise.setRGB(row, col, lerpColor(lowColor, highColor, pixelBrightness).getRGB());
                }
            }
            noiseStack[i] = noise;
        }
    }
}
