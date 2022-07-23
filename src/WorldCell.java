public class WorldCell {
    WorldPoint point;
    float x;
    float y;
    float width;
    float height;

    public WorldCell(float x, float y, float width, float height, WorldPoint point) {
        this.point = point;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
}
