import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class VoronoiGenerator extends NoiseGenerator{
   //cell properties
    float cellsPerRow = 6F;
    float lerpFactor = 0.08F;
    float cellWidth;
    float cellHeight;
    Color lowColor;
    Color highColor;
    //Color midColor;
    //this arraylist stores all the cells
    ArrayList<Cell> cells = new ArrayList<Cell>();

    public VoronoiGenerator(int textureWidth, int textureHeight, Color lowColor, Color highColor) {
        super(textureWidth, textureHeight);
        this.cellWidth = textureWidth/cellsPerRow;
        this.cellHeight = textureHeight/cellsPerRow;
        this.lowColor = lowColor;
        this.highColor = highColor;
        //midColor = new Color(255, 113, 91);
    }

    public BufferedImage generate() {
        BufferedImage noise = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = noise.createGraphics();

        //this for loop nest loops through cellsPerRow and creates a cell with a random point
        for (int row = -1; row <= cellsPerRow; row++) {
            for (int column = -1; column <= cellsPerRow; column++) {
                //this line of math generates a random point between teh bounds of the call
                Point randomPoint = new Point((float)(row + Math.random()) * cellWidth, (float)(column + Math.random()) * cellHeight);

                //creates a cell containing said random point
                Cell cell = new Cell(row , column , cellWidth, cellHeight, randomPoint);

                //adds the cell to the list of cells to keep track
                cells.add(cell);

                //test draw calls to draw out the setup
                g2.drawRect((int)(cell.x * cell.width), (int)(cell.y * cell.height), (int)cell.width, (int)cell.height);
                g2.drawOval((int)randomPoint.x, (int)randomPoint.y, 5, 5);
            }
        }
        VoronoiAlgorithm(noise);
        return noise;
    }
    
    public void VoronoiAlgorithm(BufferedImage image) {
        float maximumLength = new Point(0, 0).distanceTo(new Point(cellWidth, cellHeight));
        for (int row = 0; row < width; row++) {
            for (int col = 0; col < width; col++) {
                Point pixelAsPoint = new Point(row, col);
                Point pixelCellIndex = pixelAsPoint.getCell(cellWidth, cellHeight);
                float shortestDistance = Float.MAX_VALUE;
                ArrayList<Cell> cellsToCheck = new ArrayList<Cell>();

                //loops through all cells and check if it has the correct index or surrounding the correct index
                for (Cell cell:cells) {
                    if (Math.abs(cell.x - pixelCellIndex.x) <= 1 && Math.abs(cell.y - pixelCellIndex.y) <= 1) {
                        //adds it to the cellsToCheck list
                        cellsToCheck.add(cell);
                    }
                }

                for (Cell cell : cellsToCheck) {
                    //stores the distance from the pixel to the current point
                    float distance = pixelAsPoint.distanceTo(cell.point);
                    if (distance < shortestDistance) shortestDistance = distance;
                }

                float pixelBrightness = Math.min(shortestDistance/maximumLength + lerpFactor, 1);
                if (pixelBrightness <= 0.5) {
                    image.setRGB(row, col, lerpColor(lowColor, highColor, pixelBrightness/0.5).getRGB());
                } else {
                    image.setRGB(row, col, lerpColor(highColor, lowColor, (pixelBrightness - 0.5)/0.5).getRGB());
                }
            }
        }
    }
}
