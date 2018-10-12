import java.util.List;

/**
 * A class representing shared characteristics of fishes.
 *
 * Adaptada por Carlos Eduardo Azevedo dos Santos
 * @author David J. Barnes and Michael Kolling
 * @version 2008.03.30
 */
public abstract class Fish {
    // Whether the fish is alive or not.
    private boolean alive;
    // The fish ocean.
    private Ocean ocean;
    // The fish position in the ocean.
    private Location location;

    /**
     * Create a new fish at location in ocean.
     *
     * @param ocean The ocean currently occupied.
     * @param location The location within the ocean.
     */
    public Fish(Ocean ocean, Location location) {
        setAlive(true);
        setOcean(ocean);
        setLocation(location);
    }

    /**
     * Make this fish act - that is: make it do
     * whatever it wants/needs to do.
     * @param newFishes A list to add newly born fishes to.
     */
    abstract public void act(List<Fish> newFishes);

    /**
     * Check whether the fish is alive or not.
     * @return true if the fish is still alive.
     */
    public boolean isAlive() {
        return alive;
    }


    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    /**
     * Indicate that the fish is no longer alive.
     * It is removed from the ocean.
     */
    public void setDead() {
        setAlive(false);
        if(location != null) {
            getOcean().clear(location);
            location = null;
            setOcean(null);
        }
    }

    /**
     * Return the animal's ocean.
     * @return The animal's ocean.
     */
    public Ocean getOcean() {
        return ocean;
    }

    public void setOcean(Ocean ocean) {
        this.ocean = ocean;
    }

    /**
     * Return the animal's location.
     * @return The animal's location.
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Place the fish at the new location in the given ocean.
     * @param location The animal's new location.
     */
    public void setLocation(Location location) {
        if(location != null) {
            getOcean().clear(location);
        }
        this.location = location;
        getOcean().place(this, location);
    }
}
