import java.util.ArrayList;
import java.util.List;

public class League {
    private final List<Team> teams;

    public List<Team> getTeams() {
        return teams;
    }

    public League() {
        this.teams = new ArrayList<>();
    }

    public void addTeam(final Team team) {
        teams.add(team);
    }

    public void removeTeam(final Team team) {
        teams.remove(team);
    }

    public void printTeams() {
        for (final Team team : teams) {
            System.out.println("----------------------------The team with colors: " + team.getJersey() + "-----------------------------------");
            System.out.println("Manager: " + team.getManager().getName().toString() + " with " + team.getManager().getStarRating() + " stars");
            System.out.println("The players in this team are: ");
            for (final Player player : team.getPlayers()) {
                final Name name = player.getName();
                if (player.isGoalie()) {
                    System.out.println("- " + name.toString() + " is goalie and has stopped " + player.getGoals() + " goals");
                } else {
                    System.out.println("- " + name.toString() + " has " + player.getGoals() + " goals");
                }
            }
        }
    }
}
