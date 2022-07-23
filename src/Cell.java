public class Cell {
    Point point;
    WorldPoint worldPoint;
    float x;
    float y;
    float width;
    float height;

    public Cell(float x, float y, float width, float height, Point point) {
        this.point = point;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
}
