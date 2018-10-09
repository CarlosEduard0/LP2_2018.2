import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * (Fill in description and author info here)
 */
public class Ocean {

    private int height, width;
    private Fish[][] ocean;
    /**
     * Represent an ocean of the given dimensions.
     *
     * @param height The height of the ocean.
     * @param width  The width of the ocean.
     */
    public Ocean(int height, int width) {
        setHeight(height);
        setWidth(width);
        setOcean(new Fish[getHeight()][getWidth()]);
    }

    /**
     * Empty the ocean.
     */
    public void clear() {
        for(int row = 0; row < getHeight(); row++) {
            for(int col = 0; col < getWidth(); col++) {
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
     * of the ocean.
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
        for(Location loc : adjacent) {
            if(getFishAt(loc) == null) {
                free.add(loc);
            }
        }
        return free;
    }

    /**
     * Try to find a free location that is adjacent to the
     * given location. If there is none, return null.
     * The returned location will be within the valid bounds
     * of the ocean.
     * @param location The location from which to generate an adjacency.
     * @return A valid location within the grid area.
     */
    public Location freeAdjacentLocation(Location location) {
        List<Location> free = getFreeAdjacentLocations(location);
        Location freeLocation = null;
        if(free.size() > 0) {
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
        for(int roffset = -1; roffset <= 1; roffset++) {
            int nextRow = location.getRow() + roffset;
            if(nextRow >= 0 && nextRow < getHeight()) {
                for(int coffset = -1; coffset <= 1; coffset++) {
                    int nextCol = location.getCol() + coffset;
                    if(nextCol >= 0 && nextCol < getWidth() && (roffset != 0 || coffset != 0)) {
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
     * @param fish The fish to be placed.
     * @param location Where to place the animal.
     */
    public void place(Fish fish, Location location) {
        getOcean()[location.getRow()][location.getCol()] = fish;
    }

    /**
     * Return the fish at the given location, if any.
     *
     * @param row The desired row.
     * @param col The desired column.
     * @return The fish at the given location, or null if there is none.
     */
    public Fish getFishAt(int row, int col) {
        return getFishAt(new Location(row, col));
    }

    /**
     * Return the fish at the given location, if any.
     *
     * @param location The location of fish.
     * @return The fish at the given location, or null if there is none.
     */
    public Fish getFishAt(Location location) {
        return getOcean()[location.getRow()][location.getCol()];
    }

    /**
     * Return the height of the ocean
     * @return The height of the ocean.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Return the width of the ocean.
     * @return The width of the ocean.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Set the height of the ocean.
     * @param height The height of the ocean.
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Set the width of the ocean.
     * @param width The width of the ocean.
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Return the ocean
     * @return The ocean
     */
    public Fish[][] getOcean() {
        return ocean;
    }

    /**
     * Set the ocean.
     * @param ocean The ocean.
     */
    public void setOcean(Fish[][] ocean) {
        this.ocean = ocean;
    }
}