import java.util.List;

public interface Actor {
    /**
     * Make this actor act - that is: make it do
     * whatever it wants/needs to do.
     *
     * @param newActors A list to add newly born fishes to.
     */
    void act(List<Actor> newActors);

    /**
     * Check whether the actor is active or not.
     *
     * @return true if the actor is still active.
     */
    boolean isActive();
}
