public class WorldPoint {
    float x;
    float y;
    float z;

    public WorldPoint(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public float distanceTo(WorldPoint point2) {
        float xDisplacement = Math.abs(point2.x - x);
        float yDisplacement = Math.abs(point2.y - y);
        float zDisplacement = Math.abs(point2.z - z);
        //1 liner pythagorean theorem
        double v = Math.pow(xDisplacement, 2) + Math.pow(yDisplacement, 2) + Math.pow(zDisplacement, 2);
        return (float)Math.sqrt(v);
    }

    public Point getCell(float cellWidth, float cellHeight) {
        return new Point((float)Math.floor(x/cellWidth), (float)Math.floor(y/cellHeight));
    }

    public String toString() {
        return  "(" + String.valueOf(x) + ", " + String.valueOf(y) + ", " + String.valueOf(z) + ")";
    }

    public WorldPoint shiftZ(float shiftFactor) {
        return new WorldPoint(x, y, z + shiftFactor);
    }
}
