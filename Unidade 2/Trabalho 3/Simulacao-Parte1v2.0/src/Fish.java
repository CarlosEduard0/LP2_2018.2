import java.util.List;
import java.util.Random;

/**
 * A class representing shared characteristics of fishes.
 *
 * @author David J. Barnes and Michael Kolling
 * @version 2008.03.30
 */
public abstract class Fish implements Actor {
    // Whether the animal is alive or not.
    private boolean alive;
    // The fishe's ocean.
    private Ocean ocean;
    // The animal's position in the ocean.
    private Location location;
    // The fishe's age.
    protected int age;
    // A shared random number generator to control breeding.
    protected static final Random rand = Randomizer.getRandom();

    /**
     * Create a new animal at location in ocean.
     *
     * @param ocean    The ocean currently occupied.
     * @param location The location within the ocean.
     */
    public Fish(Ocean ocean, Location location) {
        alive = true;
        this.ocean = ocean;
        setLocation(location);
    }

    /**
     * Make this fish act - that is: make it do
     * whatever it wants/needs to do.
     *
     * @param newActors A list to add newly born fishes to.
     */
    abstract public void act(List<Actor> newActors);

    /**
     * Generate a number representing the number of births,
     * if it can breed.
     *
     * @return The number of births (may be zero).
     */
    protected int breed() {
        int births = 0;
        if (canBreed() && rand.nextDouble() <= getBreedingProbability()) {
            births = rand.nextInt(getMaxLitterSize()) + 1;
        }
        return births;
    }

    /**
     * Check whether the fish is alive or not.
     *
     * @return true if the fish is still alive.
     */
    public boolean isActive() {
        return alive;
    }

    /**
     * A ish can breed if it has reached the breeding age.
     */
    public boolean canBreed() {
        return age >= getBreedingAge();
    }

    /**
     * The likelihood of a fish breeding.
     * @return The likelihood of a fish breeding.
     */
    abstract protected int getBreedingAge();

    // The likelihood of a shark breeding.

    /**
     * The likelihood of a fish breeding.
     * @return The likelihood of a fish breeding.
     */
    abstract protected double getBreedingProbability();

    /**
     * The maximum number of births.
     * @return The maximum number of births.
     */
    abstract protected int getMaxLitterSize();

    /**
     * Increase the age. This could result in the fish's death.
     */
    protected void incrementAge() {
        age++;
        if (age > getMaxAge()) {
            setDead();
        }
    }

    /**
     * The age to which a fish can live.
     * @return The age to which a fish can live.
     */
    abstract protected int getMaxAge();

    /**
     * Indicate that the fsh is no longer alive.
     * It is removed from the ocean.
     */
    public void setDead() {
        alive = false;
        if (location != null) {
            ocean.clear(location);
            location = null;
            ocean = null;
        }
    }

    /**
     * Return the fishe's location.
     *
     * @return The fishe's location.
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Return the fishe's ocean.
     *
     * @return The fishe's ocean.
     */
    public Ocean getOcean() {
        return ocean;
    }

    /**
     * Place the fish at the new location in the given ocean.
     *
     * @param newLocation The fishe's new location.
     */
    public void setLocation(Location newLocation) {
        if (location != null) {
            ocean.clear(location);
        }
        location = newLocation;
        ocean.place(this, newLocation);
    }
}
