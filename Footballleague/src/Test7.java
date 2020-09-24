public class Test7 {

    public static void main(final String[] args) {
        final Team madridTeam = createMadridTeam();
        final Team belgiumTeam = createBelgiumTeam();
        final League league = new League();
        league.addTeam(madridTeam);
        league.addTeam(belgiumTeam);
        league.printTeams();
    }

    private static Team createMadridTeam() {
        final Name p1Name = new Name("Sergio", "", "Ramos");
        final Player p1 = new Player(p1Name, "Sergui.Ramos@gmail.com", "+00000000000");
        p1.setGoals(90);

        final Name p2Name = new Name("Eden", "", "Hazard");
        final Player p2 = new Player(p2Name, "Eden@gmail.com", "+00000000000");
        p2.setGoals(5);
        p2.setGoalie(true);

        final Name p3Name = new Name("Rodrigo", "", "Goes");
        final Player p3 = new Player(p3Name, "Rodrigo@gmail.com", "+00000000000");
        p3.setGoals(7);

        final Name m1Name = new Name("Florentino", "Perez", "Rodriguez");
        final Manager manager = new Manager(m1Name, "+00000000000", "Florentino@gmail.com", "00/00/0000", 9);

        final Team team = new Team(manager, "WhiteAndBlue");
        team.addPlayer(p1);
        team.addPlayer(p2);
        team.addPlayer(p3);
        manager.setTeam(team);
        team.setManager(manager);
        return team;
    }

    private static Team createBelgiumTeam() {
        final Name p1Name = new Name("Elias", "", "Cobbaut");
        final Player p1 = new Player(p1Name, "Elias@gmail.com", "+00000000000");
        p1.setGoals(90);

        final Name p2Name = new Name("Timothy", "", "Castagne");
        final Player p2 = new Player(p2Name, "Timothy@gmail.com", "+00000000000");
        p2.setGoals(56);

        final Name p3Name = new Name("Kevin", "De", "Bruyne");
        final Player p3 = new Player(p3Name, "Kevin@gmail.com", "+00000000000");
        p2.setGoals(9);
        p2.setGoalie(true);

        final Name m1Name = new Name("Roberto", "", "Martinez");
        final Manager manager = new Manager(m1Name, "+00000000000", "Roberto@gmail.com", "00/00/0000", 9);


        final Team team = new Team(manager, "Red");
        team.addPlayer(p1);
        team.addPlayer(p2);
        team.addPlayer(p3);
        manager.setTeam(team);
        team.setManager(manager);
        return team;
    }
}
