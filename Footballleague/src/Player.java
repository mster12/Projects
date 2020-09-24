
public class Player extends Person {

    private int goals;
    private boolean goalie;

    public Player(final Name name, final String email, final String phone) {
        super(name, phone, email);

    }

    public int getGoals() {
        return goals;
    }

    public void setGoals(final int goals) {
        this.goals = goals;
    }

    public boolean isGoalie() {
        return goalie;
    }

    public void setGoalie(final boolean goalie) {
        this.goalie = goalie;
    }

}
