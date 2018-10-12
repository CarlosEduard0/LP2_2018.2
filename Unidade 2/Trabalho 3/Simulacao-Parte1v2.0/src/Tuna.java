import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * A simple model of a tuna.
 * Tunas age, move, eat sardines, and die.
 *
 * @author David J. Barnes and Michael Kolling
 * @version 2008.03.30
 */
public class Tuna extends Fish {
    // Characteristics shared by all tunas (static oceans).

    // The age at which a tuna can start to breed.
    private static final int BREEDING_AGE = 10;
    // The age to which a tuna can live.
    private static final int MAX_AGE = 150;
    // The likelihood of a tuna breeding.
    private static final double BREEDING_PROBABILITY = 0.35;
    // The maximum number of births.
    private static final int MAX_LITTER_SIZE = 5;
    // The food value of a single sardine. In effect, this is the
    // number of steps a tuna can go before it has to eat again.
    private static final int SARDINE_FOOD_VALUE = 7;
    // A shared random number generator to control breeding.
    private static final Random rand = Randomizer.getRandom();

    // Individual characteristics (instance oceans).
    // The tuna's food level, which is increased by eating sardines.
    private int foodLevel;

    /**
     * Create a tuna. A tuna can be created as a new born (age zero
     * and not hungry) or with a random age and food level.
     *
     * @param randomAge If true, the tuna will have random age and hunger level.
     * @param ocean     The ocean currently occupied.
     * @param location  The location within the ocean.
     */
    public Tuna(boolean randomAge, Ocean ocean, Location location) {
        super(ocean, location);
        if (randomAge) {
            age = rand.nextInt(MAX_AGE);
            foodLevel = rand.nextInt(SARDINE_FOOD_VALUE);
        } else {
            age = 0;
            foodLevel = SARDINE_FOOD_VALUE;
        }
    }

    /**
     * This is what the tuna does most of the time: it hunts for
     * rabbits. In the process, it might breed, die of hunger,
     * or die of old age.
     *
     * @param newTunas A list to add newly born tunas to.
     */
    public void act(List<Actor> newTunas) {
        incrementAge();
        incrementHunger();
        if (isActive()) {
            giveBirth(newTunas);
            // Move towards a source of food if found.
            Location location = getLocation();
            Location newLocation = findFood(location);
            if (newLocation == null) {
                // No food found - try to move to a free location.
                newLocation = getOcean().freeAdjacentLocation(location);
            }
            // See if it was possible to move.
            if (newLocation != null) {
                setLocation(newLocation);
            } else {
                // Overcrowding.
                setDead();
            }
        }
    }

    @Override
    protected int getMaxAge() {
        return MAX_AGE;
    }

    /**
     * Make this tuna more hungry. This could result in the tuna's death.
     */
    private void incrementHunger() {
        foodLevel--;
        if (foodLevel <= 0) {
            setDead();
        }
    }

    /**
     * Tell the tuna to look for rabbits adjacent to its current location.
     * Only the first live rabbit is eaten.
     *
     * @param location Where in the ocean it is located.
     * @return Where food was found, or null if it wasn't.
     */
    private Location findFood(Location location) {
        Ocean ocean = getOcean();
        List<Location> adjacent = ocean.adjacentLocations(getLocation());
        Iterator<Location> it = adjacent.iterator();
        while (it.hasNext()) {
            Location where = it.next();
            Object fish = ocean.getFishAt(where);
            if (fish instanceof Sardine) {
                Sardine sardine = (Sardine) fish;
                if (sardine.isActive()) {
                    sardine.setDead();
                    foodLevel = SARDINE_FOOD_VALUE;
                    // Remove the dead sardine from the ocean.
                    return where;
                }
            }
        }
        return null;
    }

    /**
     * Check whether or not this tuna is to give birth at this step.
     * New births will be made into free adjacent locations.
     *
     * @param newTunas A list to add newly born tunas to.
     */
    private void giveBirth(List<Actor> newTunas) {
        // New tunas are born into adjacent locations.
        // Get a list of adjacent free locations.
        Ocean ocean = getOcean();
        List<Location> free = ocean.getFreeAdjacentLocations(getLocation());
        int births = breed();
        for (int b = 0; b < births && free.size() > 0; b++) {
            Location loc = free.remove(0);
            Tuna young = new Tuna(false, ocean, loc);
            newTunas.add(young);
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
}
