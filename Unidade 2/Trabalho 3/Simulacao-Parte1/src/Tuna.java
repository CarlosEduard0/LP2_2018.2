import java.util.List;
import java.util.Random;

/**
 * A simple model of a tuna.
 * Tuna age, move, breed, and die.
 * They eat herring.
 *
 * @author Richard Jones and Michael Kolling
 */
// TODO Implementar para tuna comer a sardine
public class Tuna extends Fish {

    // Characteristics shared by all tunas (static fields).

    // The age at which a tuna can start to breed.
    private static final int BREEDING_AGE = 5;
    // The age to which a tuna can live.zsa
    private static final int MAX_AGE = 40;
    // The likelihood of a tuna breeding.
    private static final double BReEDING_PROBABILITY = 0.15;
    // The maximum number of births
    private static final int MAX_LITTER_SIZE = 4;
    // A shared random number generator to control breeding.
    private static final Random rand = new Random();

    // Individual characteristics (instance fields).

    // The tuna age.
    private int age;

    /**
     * Create a new tuna. A tuna may be created with age
     * zero (a new born) or with a random age.
     *
     * @param randomAge If true, the tuna will have a random age.
     * @param ocean The ocean currently occupied.
     * @param location The location within the ocean.
     */
    public Tuna(boolean randomAge, Ocean ocean, Location location) {
        super(ocean, location);
        setAge(0);
        if(randomAge) {
            setAge(rand.nextInt(MAX_AGE));
        }
    }

    @Override
    public void act(List<Fish> newFishes) {
        // TODO
    }

    /**
     * Check whether or not this tuna is to give birth at this step.
     * New tunas will be made into free adjacent locations.
     * @param newTunas A list to add newly born rabbits to.
     */
    private void giveBirth(List<Fish> newTunas) {
        Ocean ocean = getOcean();
        List<Location> free = ocean.getFreeAdjacentLocations(getLocation());
        int births = breed();
        for(int i = 0; i < births && free.size() > 0; i++) {
            Location loc = free.remove(0);
            Tuna young = new Tuna(false, ocean, loc);
            newTunas.add(young);
        }
    }

    /**
     * Generate a number representing the number of births,
     * if it can breed.
     * @return The number of births (may be zero).
     */
    public int breed() {
        int births = 0;
        if(canBreed() && rand.nextDouble() <= BReEDING_PROBABILITY) {
            births = rand.nextInt(MAX_LITTER_SIZE) + 1;
        }
        return births;
    }

    /**
     * A tuna can breed if it has reached the breeding age.
     * @return true if the tuna can breed, false otherwise.
     */
    private boolean canBreed() {
        return getAge() >= BREEDING_AGE;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
