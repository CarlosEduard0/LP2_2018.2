import java.util.List;
import java.util.Random;

/**
 * A simple model of a sardine.
 * sardines age, move, breed, and die.
 * They eat plankton.
 * They exhibit flocking behaviour - they tend to seek company.
 * If they spot a predator close by, they panic.
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
    private static final Random rand = new Random();

    // Individual characteristics (instance fields).

    // The sardine's age.
    private int age;

    /**
     * Create a new sardine. A sardine may be created with age
     * zero (a new born) or with a random age.
     *
     * @param randomAge If true, the rabbit will have a random age.
     * @param ocean     The field currently occupied.
     * @param location  The location within the field.
     */
    public Sardine(boolean randomAge, Ocean ocean, Location location) {
        super(ocean, location);
        age = 0;
        if (randomAge) {
            age = rand.nextInt(MAX_AGE);
        }
    }

    /**
     * This is what the rabbit does most of the time - it runs
     * around. Sometimes it will breed or die of old age.
     *
     * @param newSardines A list to add newly born rabbits to.
     */
    public void act(List<Fish> newSardines) {
        incrementAge();
        if (isAlive()) {
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

    /**
     * Increase the age.
     * This could result in the sardine's death.
     */
    private void incrementAge() {
        setAge(getAge() + 1);
        if (getAge() > MAX_AGE) {
            setDead();
        }
    }

    /**
     * Check whether or not this sardine is to give birth at this step.
     * New births will be made into free adjacent locations.
     *
     * @param newSardines A list to add newly born rabbits to.
     */
    private void giveBirth(List<Fish> newSardines) {
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

    /**
     * Generate a number representing the number of births,
     * if it can breed.
     *
     * @return The number of births (may be zero).
     */
    private int breed() {
        int births = 0;
        if (canBreed() && rand.nextDouble() <= BREEDING_PROBABILITY) {
            births = rand.nextInt(MAX_LITTER_SIZE) + 1;
        }
        return births;
    }

    /**
     * A sardine can breed if it has reached the breeding age.
     *
     * @return true if the sardine can breed, false otherwise.
     */
    private boolean canBreed() {
        return age >= BREEDING_AGE;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
