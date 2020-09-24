public class Manager extends Person {

    private final String dateOfBirth;
    private int starRating;
    private Team team;


    public Manager(final Name name, final String phone, final String email, final String dateOfBirth, final int starRating) {
        super(name, phone, email);
        this.dateOfBirth = dateOfBirth;
        this.starRating = starRating;
    }

    public int getStarRating() {
        return starRating;
    }

    public void setStarRating(final int starRating) {
        if (starRating < 10 && starRating > 0)
            this.starRating = starRating;
        else System.out.println("Incorrect rating number" + starRating);
    }

    public void setTeam(final Team team) {
        this.team = team;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }
}
