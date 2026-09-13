import java.util.Arrays;

public class Problem5 {

    static class Player implements Comparable<Player> {

        private String name;
        private int matchesPlayed;
        private double battingAverage;
        private boolean injured;

        public Player(String name, int matchesPlayed,
                      double battingAverage, boolean injured) {

            this.name = name;
            this.matchesPlayed = matchesPlayed;
            this.battingAverage = battingAverage;
            this.injured = injured;
        }

        // Rule 1: Experienced players
        static boolean isDraftable(int matchesPlayed) {
            return matchesPlayed >= 10;
        }

        // Rule 2: Less experienced but fit players
        static boolean isDraftable(int matchesPlayed, boolean injured) {
            return matchesPlayed >= 5 && !injured;
        }

        // Sort by batting average in descending order
        @Override
        public int compareTo(Player other) {
            return Double.compare(other.battingAverage, this.battingAverage);
        }

        public String getName() {
            return name;
        }
    }

    static String draftAndRank(Player[] players) {

        Player[] draftable = new Player[players.length];

        int count = 0;

        for (int i = 0; i < players.length; i++) {

            if (Player.isDraftable(players[i].matchesPlayed) ||
                Player.isDraftable(players[i].matchesPlayed,
                                   players[i].injured)) {

                draftable[count] = players[i];
                count++;
            }
        }

        // Create an array containing only draftable players
        Player[] finalDraftable = Arrays.copyOf(draftable, count);

        // Sort using compareTo()
        Arrays.sort(finalDraftable);

        String result = "";

        for (int i = 0; i < finalDraftable.length; i++) {

            result = result + (i + 1) + ". "
                    + finalDraftable[i].getName();

            if (i < finalDraftable.length - 1) {
                result = result + " | ";
            }
        }

        return result;
    }

    public static void main(String[] args) {

        Player[] players = {
            new Player("Virat", 15, 48.0, false),
            new Player("Rahul", 7, 55.0, false),
            new Player("Sameer", 3, 60.0, false),
            new Player("Dev", 12, 20.0, true)
        };

        System.out.println(draftAndRank(players));
    }
}