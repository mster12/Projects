import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.nio.file.Files.readAllLines;

public class Part3 {
    final static League league = new League();

    public static void main(final String[] args) {

        int option = showMenu();

        while (option != 8) {
            switch (option) {
                case 1: {
                    System.out.println("    1. Create a new Team");
                    System.out.println("    2. Create a new Player");
                    System.out.println("    3. Create a new Manager");
                    System.out.print("Choose an option: ");
                    final Scanner keyboard = new Scanner(System.in);
                    switch (keyboard.nextInt()) {
                        case 1: {
                            createNewTeam(null);
                            break;
                        }
                        case 2: {
                            createNewPlayer();
                            break;
                        }
                        case 3: {
                            createNewManager(null);
                            break;
                        }
                        default: {
                            System.out.println("ERROR: NOT A VALID OPTION");
                        }
                    }
                    option = showMenu();
                    break;
                }
                case 2: {
                    removePlayer();
                    option = showMenu();
                    break;
                }
                case 3: {
                    final Scanner keyboard = new Scanner(System.in);
                    System.out.print("SEARCH FOR A PLAYER: ");
                    final String name = keyboard.nextLine();
                    printPlayerDetails(name);
                    option = showMenu();
                    break;
                }

                case 4: {
                    System.out.println("PRINT ALL PLAYERS");
                    printAllPlayersInATeam();
                    option = showMenu();
                    break;
                }

                case 5: {
                    System.out.println("DISPLAY ALL TEAMS: ");
                    printAllTeams();
                    option = showMenu();
                    break;
                }
                case 6: {
                    writeToFile();
                    option = showMenu();
                    break;
                }
                case 7: {
                    readFile();
                    option = showMenu();
                    break;
                }
                default: {
                    System.out.println("ERROR: NOT A CORRECT OPTION");
                }
            }

        }

    }

    private static void printAllPlayersInATeam() {
        final Scanner keyboard = new Scanner(System.in);
        final List<Team> teams = league.getTeams();
        System.out.println("Choose a team to display players: ");
        for (int i = 0; i < teams.size(); i++) {
            System.out.println("    " + i + ") Team: " + teams.get(i).getJersey());
        }
        final int i = keyboard.nextInt();
        for (final Player player : teams.get(i).getPlayers()) {
            System.out.println("- " + player.getName().toString());
        }
    }

    private static void printAllTeams() {
        if (league.getTeams().size() == 0) {
            System.out.println("WARN: NO TEAMS ARE REGISTERED IN THE LEAGUE");
        }
        for (final Team team : league.getTeams()) {
            System.out.println("- " + team.getJersey());
        }
    }

    private static void printPlayerDetails(final String name) {
        boolean found = false;
        for (final Team team : league.getTeams()) {
            for (final Player player : team.getPlayers()) {
                if (player.getName().toString().toLowerCase().contains(name)) {
                    System.out.println("    Player name: " + player.getName().toString());
                    System.out.println("    Player goals " + player.getGoals());
                    System.out.println("    Player manager " + team.getManager().getName().toString());
                    found = true;
                }
            }
        }
        if (!found)
            System.out.println("Couldn't found a player with name " + name);
    }

    private static void removePlayer() {
        System.out.print("REMOVE PLAYER: ");
        final Scanner keyboard = new Scanner(System.in);
        final String name = keyboard.nextLine();
        Player playerFound = null;
        for (final Team team : league.getTeams()) {
            for (final Player player : team.getPlayers()) {
                if (player.getName().toString().toLowerCase().contains(name)) {
                    playerFound = player;
                    System.out.println("Player with name " + name + " was removed successfully.");
                }
                if (playerFound != null) {
                    team.removePlayer(playerFound);
                    break;
                }
            }
        }
        if (playerFound == null)
            System.out.println("Couldn't found a player with name " + name);

    }

    private static void createNewPlayer() {
        System.out.println("CREATING A NEW PLAYER");
        final Scanner keyboard = new Scanner(System.in);
        System.out.print("Player full name: ");
        final Name name = createName(keyboard.nextLine());
        System.out.print("Player phone number: ");
        final String phoneNumber = keyboard.nextLine();
        System.out.print("Email: ");
        final String email = keyboard.nextLine();
        System.out.print("Is goalie (y/n): ");
        final boolean isGoalie = convertToBoolean(keyboard.nextLine());
        System.out.print("# of goals ");
        final int goals = keyboard.nextInt();

        final Player player = new Player(name, email, phoneNumber);
        player.setGoalie(isGoalie);
        player.setGoals(goals);

        System.out.println("Choose a team for your player: ");
        final Team team = printAllTeamsAndChoose(null);
        System.out.println("INFO: PLAYER CREATED CORRECTLY");
        team.addPlayer(player);
    }

    private static boolean convertToBoolean(final String nextLine) {
        if (nextLine.equalsIgnoreCase("y"))
            return true;
        return false;
    }

    private static Team createNewTeam(Manager manager) {
        final Scanner keyboard = new Scanner(System.in);
        System.out.println("CREATING A NEW TEAM");
        System.out.print("Insert team jersey: ");
        final String jersey = keyboard.nextLine();
        final Team team = new Team(manager, jersey);
        if (manager == null) {
            System.out.println("Who is the manager of this team?");
            System.out.println("    1. Choose an existing manager");
            System.out.println("    2. Create new manager");

            final int option = keyboard.nextInt();
            switch (option) {
                case 1:
                    manager = printAllMangersAndChose(team);
                    break;
                case 2:
                    manager = createNewManager(team);
                    break;
            }

        }
        team.setManager(manager);
        manager.setTeam(team);
        league.addTeam(team);
        System.out.println("INFO: TEAM CREATED CORRECTLY!");
        return team;

    }

    private static Manager printAllMangersAndChose(final Team team) {
        final List<Team> teams = league.getTeams();
        if (teams.size() == 0) {
            System.out.println("WARNING!: There are no managers in the league. Please create one:");
            return createNewManager(team);
        }
        final Scanner keyboard = new Scanner(System.in);
        for (int i = 0; i < teams.size(); i++) {
            System.out.println(i + "- Manger " + teams.get(i).getManager().getName().toString());
        }

        System.out.print("Choose one manger: ");
        return teams.get(keyboard.nextInt()).getManager();
    }

    private static Manager createNewManager(Team team) {
        System.out.println("CREATING A NEW MANAGER");
        final Scanner keyboard = new Scanner(System.in);
        System.out.print("Manager full name: ");
        final Name name = createName(keyboard.nextLine());
        System.out.print("Phone number: ");
        final String phoneNumber = keyboard.nextLine();
        System.out.print("Email: ");
        final String email = keyboard.nextLine();
        System.out.print("Date of birth: ");
        final String dateOfBirth = keyboard.nextLine();
        System.out.print("Rating starts (1-10) ");
        final int rating = keyboard.nextInt();
        final Manager manager = new Manager(name, phoneNumber, email, dateOfBirth, rating);

        if (team == null) {
            System.out.println("    Which team will be managed by this manager");
            System.out.println("        1. Choose an existing team");
            System.out.println("        2. Create new team");
            final int teamOption = keyboard.nextInt();

            if (teamOption == 1)
                team = printAllTeamsAndChoose(manager);
            else if (teamOption == 2) {
                team = createNewTeam(manager);
            }

        }
        manager.setTeam(team);
        team.setManager(manager);
        System.out.println("INFO: MANAGER CREATED CORRECTLY!");
        return manager;
    }

    private static Team printAllTeamsAndChoose(final Manager manager) {
        if (league.getTeams().size() == 0) {
            System.out.print("There are no teams in the league. Insert a team: ");
            return createNewTeam(manager);
        }
        final Scanner keyboard = new Scanner(System.in);

        final List<Team> teams = league.getTeams();
        for (int i = 0; i < teams.size(); i++) {
            System.out.println(i + ") Team: " + teams.get(i).getJersey());
        }
        System.out.print("Choose one team: ");
        return teams.get(keyboard.nextInt());
    }

    private static Name createName(final String name) {
        final String[] nameList = name.split(" ");
        if (nameList.length == 1)
            return new Name(nameList[0], "", "");
        if (nameList.length == 2)
            return new Name(nameList[0], "", nameList[1]);

        return new Name(nameList[0], nameList[1], nameList[2]);

    }

    private static int showMenu() {
        final Scanner keyboard = new Scanner(System.in);
        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.println("|  1. Create a new Team Player or Manager and add Player/Manager to a Team.                 |");
        System.out.println("|  2. Remove a Player.                                                                      |");
        System.out.println("|  3. Search for a Player by supplying the Player name. Display goals and Manager details.  |");
        System.out.println("|  4. Display all players in a particular Team.                                             |");
        System.out.println("|  5. Display all Teams in the League.                                                      |");
        System.out.println("|  6. Save all information to a text file.                                                  |");
        System.out.println("|  7. Load information from a text file.                                                    |");
        System.out.println("|  8. Quit                                                                                  |");
        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.print("Choose an option: ");
        return keyboard.nextInt();

    }

    private static void writeToFile() {
        try (final BufferedWriter writer = new BufferedWriter(new FileWriter("temp.csv"))) {
            for (final Team team : league.getTeams()) {
                final Manager manager = team.getManager();
                writer.write(serializeTeam(team) + ";" + serializeManager(manager) + ";" + serializePlayers(team.getPlayers()));
            }
            writer.flush();
            writer.close();
            System.out.println("INFO: FILE CREATED CORRECTLY");
        } catch (final Exception e) {
            System.out.println("ERROR: COULDN'T CREATE THE FILE");
            e.printStackTrace();
        }

    }

    private static String serializePlayers(final ArrayList<Player> players) {
        String playerStr = "";
        for (final Player player : players) {
            playerStr = playerStr + "; " + player.getName().toString() + "," + player.getEmail() + "," + player.getPhone() + "," + player.getGoals() + "," + player.isGoalie();
        }
        return playerStr;
    }

    private static String serializeManager(final Manager manager) {
        return manager.getName().toString() + "," + manager.getPhone() + "," + manager.getEmail() + "," + manager.getDateOfBirth() + "," + manager.getStarRating();
    }

    private static String serializeTeam(final Team team) {
        return team.getJersey();
    }

    private static void readFile() {
        System.out.print("Provide complete path of the file: ");
        final Scanner keyboard = new Scanner(System.in);


        try {
            final List<String> teams = readAllLines(Paths.get(keyboard.nextLine()));
            for (final String teamStr : teams) {
                final String[] split = teamStr.replace(";;", ";").split(";");
                final Team team = deserializeTeam(split[0]);
                final Manager manager = deserializeManager(split[1]);
                final List<Player> players = deserializePlayers(split);
                team.setManager(manager);
                manager.setTeam(team);
                for (final Player player : players) {
                    team.addPlayer(player);
                }
                league.addTeam(team);
            }
        } catch (final IOException e) {
            e.printStackTrace();
        }
        System.out.println("INFO: File loaded correctly");
    }

    private static List<Player> deserializePlayers(final String[] playersStr) {
        final List<Player> players = new ArrayList<>();
        if (playersStr.length > 2) {
            for (int i = 2; i < playersStr.length; i++) {
                final String playerStr = playersStr[i];
                final String[] split1 = playerStr.split(",");
                final Player player = new Player(createName(split1[0]), split1[1], split1[2]);
                player.setGoals(Integer.parseInt(split1[3]));
                player.setGoalie(Boolean.parseBoolean(split1[4]));
                players.add(player);
            }
        }
        return players;
    }

    private static Manager deserializeManager(final String managerStr) {
        final String[] split = managerStr.split(",");
        return new Manager(createName(split[0]), split[1], split[2], split[3], Integer.parseInt(split[4]));
    }

    private static Team deserializeTeam(final String s) {
        return new Team(null, s);
    }
}
