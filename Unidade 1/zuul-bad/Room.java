import java.util.HashMap;

/**
 * Class Room - a room in an adventure game.
 * <p>
 * This class is part of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game.
 * <p>
 * A "Room" represents one location in the scenery of the game.  It is
 * connected to other rooms via exits.  The exits are labelled north,
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 *
 * @author Michael Kolling and David J. Barnes
 * @version 2008.03.30
 */
class Room {
    private String description;
    private HashMap<String, Room> exits;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     *
     * @param description The room's description.
     */
    Room(String description) {
        this.description = description;
        this.exits = new HashMap<>();
    }

    void setExit(String direction, Room neighbor) {
        exits.put(direction, neighbor);
    }

    /**
     * Retorna a sala que é alcançada se sairmos desta
     * sala na direção "direction". Se não houver nenhuma sala nessa
     * direção, retorna nulo.
     */
    Room getExit(String direction) {
        return exits.get(direction);
    }

    /**
     * @return The description of the room.
     */
    String getDescription() {
        return description;
    }

    /**
     * Retorna uma descrição das saídas da sala,
     * por exemplo, "Exits: north west".
     * @return Uma descrição das saídas disponíveis.
     */
    String getExitString() {
        StringBuilder result = new StringBuilder();
        for(String key : exits.keySet()) {
            result.append(key).append(" ");
        }
        return result.toString();
    }
}
