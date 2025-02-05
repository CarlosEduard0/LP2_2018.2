import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Represent a rectangular grid of ocean positions.
 * Each position is able to store a single fish.
 *
 * @author David J. Barnes and Michael Kolling
 * @version 2008.03.30
 */
public class Ocean {
    // A random number generator for providing random locations.
    private static final Random rand = Randomizer.getRandom();

    // The height and width of the ocean.
    private int height, width;
    // Storage for the fishes.
    private Fish[][] ocean;

    /**
     * Represent a ocean of the given dimensions.
     *
     * @param height The height of the ocean.
     * @param width  The width of the ocean.
     */
    public Ocean(int height, int width) {
        this.height = height;
        this.width = width;
        ocean = new Fish[height][width];
    }

    /**
     * Empty the ocean.
     */
    public void clear() {
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                ocean[row][col] = null;
            }
        }
    }

    /**
     * Clear the given location.
     *
     * @param location The location to clear.
     */
    public void clear(Location location) {
        ocean[location.getRow()][location.getCol()] = null;
    }

    /**
     * Place an fish at the given location.
     * If there is already an fish at the location it will
     * be lost.
     *
     * @param fish The fish to be placed.
     * @param row  Row coordinate of the location.
     * @param col  Column coordinate of the location.
     */
    public void place(Fish fish, int row, int col) {
        place(fish, new Location(row, col));
    }

    /**
     * Place an fish at the given location.
     * If there is already an fish at the location it will
     * be lost.
     *
     * @param fish     The fish to be placed.
     * @param location Where to place the fish.
     */
    public void place(Fish fish, Location location) {
        ocean[location.getRow()][location.getCol()] = fish;
    }

    /**
     * Return the animal at the given location, if any.
     *
     * @param location Where in the ocean.
     * @return The fish at the given location, or null if there is none.
     */
    public Fish getFishAt(Location location) {
        return getFishAt(location.getRow(), location.getCol());
    }

    /**
     * Return the fish at the given location, if any.
     *
     * @param row The desired row.
     * @param col The desired column.
     * @return The fish at the given location, or null if there is none.
     */
    public Fish getFishAt(int row, int col) {
        return ocean[row][col];
    }

    /**
     * Generate a random location that is adjacent to the
     * given location, or is the same location.
     * The returned location will be within the valid bounds
     * of the ocean.
     *
     * @param location The location from which to generate an adjacency.
     * @return A valid location within the grid area.
     */
    public Location randomAdjacentLocation(Location location) {
        List<Location> adjacent = adjacentLocations(location);
        return adjacent.get(0);
    }

    /**
     * Get a shuffled list of the free adjacent locations.
     *
     * @param location Get locations adjacent to this.
     * @return A list of free adjacent locations.
     */
    public List<Location> getFreeAdjacentLocations(Location location) {
        List<Location> free = new LinkedList<Location>();
        List<Location> adjacent = adjacentLocations(location);
        for (Location next : adjacent) {
            if (getFishAt(next) == null) {
                free.add(next);
            }
        }
        return free;
    }

    /**
     * Try to find a free location that is adjacent to the
     * given location. If there is none, return null.
     * The returned location will be within the valid bounds
     * of the ocean.
     *
     * @param location The location from which to generate an adjacency.
     * @return A valid location within the grid area.
     */
    public Location freeAdjacentLocation(Location location) {
        // The available free ones.
        List<Location> free = getFreeAdjacentLocations(location);
        if (free.size() > 0) {
            return free.get(0);
        } else {
            return null;
        }
    }

    /**
     * Return a shuffled list of locations adjacent to the given one.
     * The list will not include the location itself.
     * All locations will lie within the grid.
     *
     * @param location The location from which to generate adjacencies.
     * @return A list of locations adjacent to that given.
     */
    public List<Location> adjacentLocations(Location location) {
        assert location != null : "Null location passed to adjacentLocations";
        // The list of locations to be returned.
        List<Location> locations = new LinkedList<Location>();
        if (location != null) {
            int row = location.getRow();
            int col = location.getCol();
            for (int roffset = -1; roffset <= 1; roffset++) {
                int nextRow = row + roffset;
                if (nextRow >= 0 && nextRow < height) {
                    for (int coffset = -1; coffset <= 1; coffset++) {
                        int nextCol = col + coffset;
                        // Exclude invalid locations and the original location.
                        if (nextCol >= 0 && nextCol < width && (roffset != 0 || coffset != 0)) {
                            locations.add(new Location(nextRow, nextCol));
                        }
                    }
                }
            }

            // Shuffle the list. Several other methods rely on the list
            // being in a random order.
            Collections.shuffle(locations, rand);
        }
        return locations;
    }

    /**
     * Return the height of the ocean.
     *
     * @return The height of the ocean.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Return the width of the ocean.
     *
     * @return The width of the ocean.
     */
    public int getWidth() {
        return width;
    }
}
