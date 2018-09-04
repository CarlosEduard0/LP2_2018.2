public class Vehicle {
    // A unique ID for this vehicle.
    private String id;
    // The destination of this vehicle.
    private String destination;

    public Vehicle(String id) {
        this.id = id;
    }

    /**
     * Return the ID of the vehicle.
     * @return The ID of the vehicle.
     */
    public String getID() {
        return this.id;
    }

    /**
     * Return the destination of the vehicle.
     * @return The destination of the vehicle.
     */
    public String getDestination()
    {
        return destination;
    }

    /**
     * Set the intented destination of the vehicle.
     * @param destination The intended destination.
     */
    public void setDestination(String destination)
    {
        this.destination = destination;
    }
}