import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * A simple model of a shark.
 * Sharks age, move, breed, and die.
 * Sharks eat groper or herring but they prefer groper.
 * Sharks are loners - they prefer not to swim next to each other
 *
 * @author Richard Jones and Michael Kolling
 */
public class Shark extends Fish {
    // Characteristics shared by all shakrs (static fields).

    // The age at which a shark can start to breed.
    private static final int BREEDING_AGE = 10;
    // The age to which a shark can live.
    private static final int MAX_AGE = 150;
    // The likelihood of a shark breeding.
    private static final double BREEDING_PROBABILITY = 0.35;
    // The maximum number of births.
    private static final int MAX_LITTER_SIZE = 5;
    // The food value of a single tuna. In effect, this is the
    // number of steps a shark can go before it has to eat again.
    private static final int TUNA_FOOD_VALUE = 7;
    // A shared random number generator to control breeding.
    private static final Random rand = new Random();

    // Individual characteristics (instance fields).
    // The shark's age
    private int age;
    // The shark's food level, which is increased by eating tuna and sardine.
    private int foodLevel;

    /**
     * Create a shark. A shark can be created as a new born (age zero
     * and not hungry) or with a random age and food level.
     *
     * @param randomAge If true, the shark will have random age and hunger level.
     * @param ocean The ocean currently occupied.
     * @param location The location within the ocean.
     */
    public Shark(boolean randomAge, Ocean ocean, Location location) {
        super(ocean, location);
        if(randomAge) {
            setAge(rand.nextInt(MAX_AGE));
            setFoodLevel(rand.nextInt(TUNA_FOOD_VALUE));
        } else {
            setAge(0);
            setFoodLevel(TUNA_FOOD_VALUE);
        }
    }

    /**
     * This is what the shark does most of the time: it hunts for
     * tunas. In the process, it might breed, die of hunger,
     * or die of old age.
     * @param newSharks A list to add newly born sharks to.
     */
    public void act(List<Fish> newSharks) {
        incrementAge();
        incrementHunger();
        if(isAlive()) {
            giveBirth(newSharks);
            // Move towards a source of food if found.
            Location location = getLocation();
            Location newLocation = findFood(location);
            if(newLocation == null) {
                // No food found - try to move to a free location.
                newLocation = getOcean().freeAdjacentLocation(location);
            }
            // See if it was possible to move.
            if(newLocation != null) {
                setLocation(newLocation);
            } else {
                // Overcrowding
                setDead();
            }
        }
    }

    /**
     * Increase the age. This could result in the shark's death.
     */
    private void incrementAge() {
        setAge(getAge() + 1);
        if(getAge() > MAX_AGE) {
            setDead();
        }
    }

    /**
     * Make this fox more hungry. This could result in the shark's death.
     */
    private void incrementHunger() {
        setFoodLevel(getFoodLevel() - 1);
        if(getFoodLevel() <= 0) {
            setDead();
        }
    }

    /**
     * Tell the fox to look for rabbits adjacent to its current location.
     * Only the first live rabbit is eaten.
     * @param location Where in the field it is located.
     * @return Where food was found, or null if it wasn't.
     */
    private Location findFood(Location location) {
        Ocean ocean = getOcean();
        List<Location> adjacent = ocean.adjacentLocations(getLocation());
        Iterator<Location> it = adjacent.iterator();
        Location where = null;
        while(it.hasNext()) {
            where = it.next();
            Fish fish = ocean.getFishAt(where);
            if(fish instanceof Tuna) {
                Tuna tuna = (Tuna) fish;
                if(tuna.isAlive()) {
                    tuna.setDead();
                    setFoodLevel(TUNA_FOOD_VALUE);
                }
            }
        }
        return where;
    }

    /**
     * Check whether or not this shark is to give birth at this step.
     * New births will be made into free adjacent locations.
     * @param newSharks A list to add newly born sharks to.
     */
    private void giveBirth(List<Fish> newSharks) {
        // New sharks are born into adjacent locations.
        // Get a list of adjacent free locations.
        Ocean ocean = getOcean();
        List<Location> free = ocean.getFreeAdjacentLocations(getLocation());
        int births = breed();
        for(int b = 0; b < births && free.size() > 0; b++) {
            Location loc = free.remove(0);
            Shark young = new Shark(false, ocean, loc);
            newSharks.add(young);
        }
    }

    /**
     * Generate a number representing the number of births,
     * if it can breed.
     * @return The number of births (may be zero).
     */
    private int breed() {
         int births = 0;
         if(canBreed() && rand.nextDouble() <= BREEDING_PROBABILITY) {
             births = rand.nextInt(MAX_LITTER_SIZE) + 1;
         }
         return births;
    }

    /**
     * A shark can breed if it has reached the breeding age.
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

    public int getFoodLevel() {
        return foodLevel;
    }

    public void setFoodLevel(int foodLevel) {
        this.foodLevel = foodLevel;
    }
}
