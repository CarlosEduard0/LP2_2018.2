import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Represent a rectangular grid of field positions.
 * Each position is able to store a single animal.
 *
 * Adaptada por Carlos Eduardo Azevedo dos Santos
 * @author David J. Barnes and Michael Kolling
 * @version 2008.03.30
 */
public class Field {
    // The depth and width of the field.
    private int depth, width;
    // Storage for the animals.
    private Object[][] field;

    /**
     * Represent a field of the given dimensions.
     * @param depth The depth of the field.
     * @param width The width of the field.
     */
    public Field(int depth, int width) {
        setDepth(depth);
        setWidth(width);
        setField(new Object[getDepth()][getWidth()]);
    }

    /**
     * Empty the field.
     */
    public void clear() {
        for (int row = 0; row < getDepth(); row++) {
            for (int col = 0; col < getWidth(); col++) {
                place(null, new Location(row, col));
            }
        }
    }

    /**
     * Clear the given location.
     * @param location The location to clear.
     */
    public void clear(Location location) {
        place(null, location);
    }

    /**
     * Generate a random location that is adjacent to the
     * given location, or is the same location.
     * The returned location will be within the valid bounds
     * of the field.
     * @param location The location from which to generate an adjacency.
     * @return A valid location within the grid area.
     */
    public Location randomAdjacentLocation(Location location) {
        return adjacentLocations(location).get(0);
    }

    /**
     * Get a shuffled list of the free adjacent locations.
     * @param location Get locations adjacent to this.
     * @return A list of free adjacent locations.
     */
    public List<Location> getFreeAdjacentLocations(Location location) {
        List<Location> free = new LinkedList<>();
        List<Location> adjacent = adjacentLocations(location);
        for (Location loc : adjacent) {
            if (getObjectAt(loc) == null) {
                free.add(loc);
            }
        }
        return free;
    }

    /**
     * Try to find a free location that is adjacent to the
     * given location. If there is none, return null.
     * The returned location will be within the valid bounds
     * of the field.
     * @param location The location from which to generate an adjacency.
     * @return A valid location within the grid area.
     */
    public Location freeAdjacentLocation(Location location) {
        List<Location> free = getFreeAdjacentLocations(location);
        Location freeLocation = null;
        if (free.size() > 0) {
            freeLocation = free.get(0);
        }
        return freeLocation;
    }

    /**
     * Return a shuffled list of locations adjacent to the given one.
     * The list will not include the location itself.
     * All locations will lie within the grid.
     * @param location The location from which to generate adjacencies.
     * @return A list of locations adjacent to that given.
     */
    public List<Location> adjacentLocations(Location location) {
        List<Location> locations = new LinkedList<>();
        for (int roffset = -1; roffset <= 1; roffset++) {
            int nextRow = location.getRow() + roffset;
            if (nextRow >= 0 && nextRow < getDepth()) {
                for (int coffset = -1; coffset <= -1; coffset++) {
                    int nextCol = location.getCol() + coffset;
                    if (nextCol >= 0 && nextCol < getWidth() && (roffset != 0 || coffset != 0)) {
                        locations.add(new Location(nextRow, nextCol));
                    }
                }
            }
        }
        Collections.shuffle(locations);
        return locations;
    }

    /**
     * Place an object at the given location.
     * If there is already an animal at the location it will
     * be lost.
     * @param value The animal to be placed.
     * @param location Where to place the animal.
     */
    public void place(Object value, Location location) {
        getField()[location.getRow()][location.getCol()] = value;
    }

    /**
     * Return the object at the given location, if any.
     * @param location Where in the field.
     * @return The object at the given location, or null if there is none.
     */
    public Object getObjectAt(Location location) {
        return getField()[location.getRow()][location.getCol()];
    }

    /**
     * Return the depth of the field.
     * @return The depth of the field.
     */
    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    /**
     * Return the width of the field.
     * @return The width of the field.
     */
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Return the field
     * @return The field
     */
    public Object[][] getField() {
        return field;
    }

    public void setField(Object[][] field) {
        this.field = field;
    }
}
