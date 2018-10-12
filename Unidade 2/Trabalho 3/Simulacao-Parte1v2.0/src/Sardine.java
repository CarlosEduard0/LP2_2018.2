import java.util.List;
import java.util.Random;

/**
 * A simple model of a sardine.
 * Sardines age, move, breed, and die.
 *
 * @author David J. Barnes and Michael Kolling
 * @version 2008.03.30
 */
public class Sardine extends Fish {
    // Characteristics shared by all sardines (static fields).

    // The age at which a sardine can start to breed.
    private static final int BREEDING_AGE = 5;
    // The age to which a sardine can live.
    private static final int MAX_AGE = 40;
    // The likelihood of a sardine breeding.
    private static final double BREEDING_PROBABILITY = 0.15;
    // The maximum number of births.
    private static final int MAX_LITTER_SIZE = 4;
    // A shared random number generator to control breeding.
    private static final Random rand = Randomizer.getRandom();

    /**
     * Create a new sardine. A sardine may be created with age
     * zero (a new born) or with a random age.
     *
     * @param randomAge If true, the sardine will have a random age.
     * @param ocean     The ocean currently occupied.
     * @param location  The location within the ocean.
     */
    public Sardine(boolean randomAge, Ocean ocean, Location location) {
        super(ocean, location);
        age = 0;
        if (randomAge) {
            age = rand.nextInt(MAX_AGE);
        }
    }

    /**
     * This is what the sardine does most of the time - it runs
     * around. Sometimes it will breed or die of old age.
     *
     * @param newSardines A list to add newly born sardines to.
     */
    public void act(List<Actor> newSardines) {
        incrementAge();
        if (isActive()) {
            giveBirth(newSardines);
            // Try to move into a free location.
            Location newLocation = getOcean().freeAdjacentLocation(getLocation());
            if (newLocation != null) {
                setLocation(newLocation);
            } else {
                // Overcrowding.
                setDead();
            }
        }
    }

    @Override
    protected int getBreedingAge() {
        return BREEDING_AGE;
    }

    @Override
    protected double getBreedingProbability() {
        return BREEDING_PROBABILITY;
    }

    @Override
    protected int getMaxLitterSize() {
        return MAX_LITTER_SIZE;
    }

    @Override
    protected int getMaxAge() {
        return MAX_AGE;
    }

    /**
     * Check whether or not this sardine is to give birth at this step.
     * New births will be made into free adjacent locations.
     *
     * @param newSardines A list to add newly born sardines to.
     */
    private void giveBirth(List<Actor> newSardines) {
        // New sardines are born into adjacent locations.
        // Get a list of adjacent free locations.
        Ocean ocean = getOcean();
        List<Location> free = ocean.getFreeAdjacentLocations(getLocation());
        int births = breed();
        for (int b = 0; b < births && free.size() > 0; b++) {
            Location loc = free.remove(0);
            Sardine young = new Sardine(false, ocean, loc);
            newSardines.add(young);
        }
    }
}
