import java.util.LinkedList;
import java.util.List;

public class Field {
    private int depth, width;
    private Object[][] fields;

    public Field(int depth, int width) {
        setDepth(depth);
        setWidth(width);
        setFields(new Object[getDepth()][getWidth()]);
    }

    public void clear() {
        for (int row = 0; row < getDepth(); row++){
            for(int col = 0; col <getWidth(); col++){
                place(null, new Location(row, col));
            }
        }
    }

    public void clear(Location location) {
        place(null, location);
    }

    public Location randomAdjacentLocation(Location location) {
        List<Location> adjactent = adjacentLocations(location);
        return adjactent.get(0);
    }

    public List<Location> getFreeAdjacentLocations(Location location) {
        List<Location> free = new LinkedList<>();
        List<Location> adjacent = adjacentLocations(location);
        for(Location loc : adjacent) {
            if(getObjectAt(location) == null) {
                free.add(loc);
            }
        }
        return free;
    }

    public List<Location> adjacentLocations(Location location) {
        List<Location> locations = new LinkedList<>();
        int row = location.getRow();
        int col = location.getCol();
        for(int offset = -1; offset <= 1; offset++) {
            int nextRow = row + offset;

        }
    }

    public void place(Object value, Location location) {
        getFields()[location.getRow()][location.getCol()] = value;
    }

    public Object getObjectAt(Location location) {
        return getFields()[location.getRow()][location.getCol()];
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public Object[][] getFields() {
        return fields;
    }

    public void setFields(Object[][] fields) {
        this.fields = fields;
    }
}
