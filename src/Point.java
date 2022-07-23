public class Point {
    float x;
    float y;

    public Point(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float distanceTo(Point point2) {
        float xDisplacement = Math.abs(point2.x - x);
        float yDisplacement = Math.abs(point2.y - y);
        //1 liner pythagorean theorem
        double v = Math.pow(xDisplacement, 2) + Math.pow(yDisplacement, 2);
        return (float)Math.sqrt(v);
    }

    public Point getCell(float cellWidth, float cellHeight) {
        return new Point((float)Math.floor(x/cellWidth), (float)Math.floor(y/cellHeight));
    }

    public String toString() {
        return  "(" + String.valueOf(x) + ", " + String.valueOf(y) + ")";
    }
}
