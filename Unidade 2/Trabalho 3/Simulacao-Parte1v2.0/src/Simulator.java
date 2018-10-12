import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * A simple predator-prey simulator, based on a rectangular ocean
 * containing tunas and sardines.
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
    // The probability that a sardines will be created in any given grid position.
    private static final double SARDINE_CREATION_PROBABILITY = 0.08;
    // The probability that a sharks will be created in any given grid position.
    private static final double SHARK_CREATION_PROBABILITY = 0.01;

    // List of actors in the ocean.
    private List<Actor> actors;
    // The current state of the ocean.
    private Ocean ocean;
    // The current step of the simulation.
    private int step;
    // A graphical view of the simulation.
    private SimulatorView view;

    /**
     * Construct a simulation ocean with default size.
     */
    public Simulator() {
        this(DEFAULT_HEIGHT, DEFAULT_WIDTH);
    }

    /**
     * Create a simulation ocean with the given size.
     *
     * @param height Depth of the ocean. Must be greater than zero.
     * @param width  Width of the ocean. Must be greater than zero.
     */
    public Simulator(int height, int width) {
        if (width <= 0 || height <= 0) {
            System.out.println("The dimensions must be greater than zero.");
            System.out.println("Using default values.");
            height = DEFAULT_HEIGHT;
            width = DEFAULT_WIDTH;
        }

        actors = new ArrayList<Actor>();
        ocean = new Ocean(height, width);

        // Create a view of the state of each location in the ocean.
        view = new SimulatorView(height, width);
        view.setColor(Sardine.class, Color.orange);
        view.setColor(Tuna.class, Color.blue);
        view.setColor(Shark.class, Color.red);

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
     *
     * @param numSteps The number of steps to run for.
     */
    public void simulate(int numSteps) {
        for (int step = 1; step <= numSteps && view.isViable(ocean); step++) {
            simulateOneStep();
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Run the simulation from its current state for a single step.
     * Iterate over the whole ocean updating the state of each
     * tuna and sardine.
     */
    public void simulateOneStep() {
        step++;

        // Provide space for newborn actors.
        List<Actor> newActors = new ArrayList<Actor>();
        // Let all rabbits act.
        for (Iterator<Actor> it = actors.iterator(); it.hasNext(); ) {
            Actor fish = it.next();
            fish.act(newActors);
            if (!fish.isActive()) {
                it.remove();
            }
        }

        // Add the newly born foxes and rabbits to the main lists.
        actors.addAll(newActors);

        view.showStatus(step, ocean);
    }

    /**
     * Reset the simulation to a starting position.
     */
    public void reset() {
        step = 0;
        actors.clear();
        populate();

        // Show the starting state in the view.
        view.showStatus(step, ocean);
    }

    /**
     * Randomly populate the ocean with tunas and sardines.
     */
    private void populate() {
        Random rand = Randomizer.getRandom();
        ocean.clear();
        for (int row = 0; row < ocean.getHeight(); row++) {
            for (int col = 0; col < ocean.getWidth(); col++) {
                if (rand.nextDouble() <= TUNA_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Tuna tuna = new Tuna(true, ocean, location);
                    actors.add(tuna);
                } else if (rand.nextDouble() <= SARDINE_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Sardine sardine = new Sardine(true, ocean, location);
                    actors.add(sardine);
                } else if (rand.nextDouble() <= SHARK_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Shark shark = new Shark(true, ocean, location);
                    actors.add(shark);
                }
                // else leave the location empty.
            }
        }
    }
}
