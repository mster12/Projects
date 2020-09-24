import java.util.ArrayList;

public class Team {
    private Manager manager;
    private final String jersey;
    private final ArrayList<Player> players;

    public Team(final Manager manager, final String jersey) {
        this.manager = manager;
        this.jersey = jersey;
        this.players = new ArrayList<Player>();
    }

    public void setManager(final Manager manager) {
        this.manager = manager;

    }

    public Manager getManager() {
        return manager;

    }

    public void removeManager() {
        manager = null;

    }

    public void addPlayer(final Player player) {
        this.players.add(player);

    }

    public void removePlayer(final Player player) {
        players.remove(player);

    }

    public String getJersey() {
        return jersey;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }
}
