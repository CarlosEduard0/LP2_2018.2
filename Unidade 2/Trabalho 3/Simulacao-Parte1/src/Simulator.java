import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * A simple predator-prey simulator, based on a rectangular ocean
 * containing tunas, sharks and sardines.
 *
 * @author David J. Barnes and Michael Kolling
 * @version 2008.03.30
 */
public class Simulator {
    // Constants representing configuration information for the simulation.
    // The default width for the grid.
    private static final int DEFAULT_WIDTH = 50;
    // The default height of the grid.
    private static final int DEFAULT_HEIGHT = 50;
    // The probability that a tuna will be created in any given grid position.
    private static final double TUNA_CREATION_PROBABILITY = 0.02;
    // The probability that a shark will be created in any given grid position.
    private static final double SHARK_CREATION_PROBABILITY = 0.06;
    // The probability that a sardine will be created in any given grid position.
    private static final double SARDINE_CREATION_PROBABILITY = 0.02;

    // List of animals in the ocean.
    private List<Fish> fishes;
    // The current state of the ocean.
    private Ocean ocean;
    // The current step of the simulation.
    private int step;
    // A graphical view of the simulation.
    private SimulatorView simView;

    /**
     * Construct a simulation ocean with default size.
     */
    public Simulator() {
        this(DEFAULT_HEIGHT, DEFAULT_WIDTH);
    }

    /**
     * Create a simulation ocean with the given size.
     * @param height Height of the ocean. Must be greater than zero.
     * @param width Width of the ocean. Must be greater than zero.
     */
    public Simulator(int height, int width) {
        if(width <= 0 || height <= 0) {
            System.out.println("The dimensions must be greater than zero.");
            System.out.println("Using default values.");;
            height = DEFAULT_HEIGHT;
            width = DEFAULT_WIDTH;
        }

        setFishes(new ArrayList<Fish>());
        setOcean(new Ocean(height, width));

        // Create a view of the state of each location in the ocean.
        setSimView(new SimulatorView(height, width));
        getSimView().setColor(Tuna.class, Color.blue);
        getSimView().setColor(Sardine.class, Color.gray);
        getSimView().setColor(Shark.class, Color.red);

        // Setup a valid starting point.
        reset();
    }

    /**
     * Run the simulation from its current state for a reasonably long period,
     * e.g. 500 steps.
     */
    public void runLongSimulation() {
        simulate(500);
    }

    /**
     * Run the simulation from its current state for the given number of steps.
     * Stop before the given number of steps if it ceases to be viable.
     * @param numSteps The number of steps to run for.
     */
    public void simulate(int numSteps) {
        for(int step = 1; step <= numSteps && getSimView().isViable(getOcean()); step++) {
            simulateOneStep();
        }
    }

    /**
     * Run the simulation from its current state for a single step.
     * Iterate over the whole field updating the state of each
     * tuna, sardine and shark.
     */
    public void simulateOneStep() {
        setStep(getStep() + 1);

        // Provide space for newborn fishes.
        List<Fish> newFishes = new ArrayList<>();
        // Let all fishes act.
        for(Iterator<Fish> it = getFishes().iterator(); it.hasNext(); ) {
            Fish fish = it.next();
            fish.act(newFishes);
            if(!fish.isAlive()) {
                it.remove();
            }
        }

        // Add the newly born tuna, sardine and shark to the main lists.
        setFishes(newFishes);
        getSimView().showStatus(getStep(), getOcean());
    }

    /**
     * Reset the simulation to a starting position.
     */
    public void reset() {
        setStep(0);
        fishes.clear();
        populate();

        getSimView().showStatus(getStep(), getOcean());
    }

    /**
     * Randomly populate the ocean with tunas, sardines and sharks.
     */
    private void populate() {
        Random rand = new Random();
        getOcean().clear();
        for(int row = 0; row < ocean.getHeight(); row++) {
            for(int col = 0; col < ocean.getWidth(); col++) {
                if(rand.nextDouble() <= TUNA_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Tuna tuna = new Tuna(true, ocean, location);
                    fishes.add(tuna);
                } else if(rand.nextDouble() <= SARDINE_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Sardine sardine = new Sardine(true, ocean, location);
                    fishes.add(sardine);
                } else if(rand.nextDouble() <= SHARK_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Shark shark = new Shark(true, ocean, location);
                    fishes.add(shark);
                }
                // else leave the location empty.
            }
        }
    }

    public List<Fish> getFishes() {
        return fishes;
    }

    public void setFishes(List<Fish> fishes) {
        this.fishes = fishes;
    }

    public Ocean getOcean() {
        return ocean;
    }

    public void setOcean(Ocean ocean) {
        this.ocean = ocean;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public SimulatorView getSimView() {
        return simView;
    }

    public void setSimView(SimulatorView simView) {
        this.simView = simView;
    }
}
