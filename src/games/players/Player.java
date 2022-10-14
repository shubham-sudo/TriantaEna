package games.players;

public abstract class Player {
    private static int ID = 0;
    private final int id;
    private final String name;

    /**
     * Create a new games.players.Player with the name provided and static ID
     * 
     * @param name name of the player
     */
    public Player(String name) throws IllegalArgumentException {
        if (name.trim().isEmpty()) {
            throw new IllegalArgumentException("Player name can't be empty");
        }
        this.id = ++ID;
        this.name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
    }

    /**
     * Getter method for the name of the player
     * 
     * @return string name of the player
     */
    public String name() {
        return this.name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public boolean equals(Object o) {
        return o instanceof Player && (((Player) o).id == this.id);
    }
}
