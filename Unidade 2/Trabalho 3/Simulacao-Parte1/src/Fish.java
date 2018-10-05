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
    // The fish field.
    private Field field;
    // The fish position in the field.
    private Location location;

    /**
     * Create a new fish at location in field.
     *
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Fish(Field field, Location location) {
        setAlive(true);
        setField(field);
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
     * It is removed from the field.
     */
    public void setDead() {
        setAlive(false);
        if(location != null) {
            getField().clear(location);
            setLocation(null);
            setField(null);
        }
    }

    /**
     * Return the animal's field.
     * @return The animal's field.
     */
    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    /**
     * Return the animal's location.
     * @return The animal's location.
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Place the fish at the new location in the given field.
     * @param location The animal's new location.
     */
    public void setLocation(Location location) {
        if(location != null) {
            getField().clear(location);
        }
        this.location = location;
        getField().place(this, location);
    }
}
