import java.io.FileInputStream;
import java.io.IOException;

/**
 * Refactored solutions to the 2022 Advent of code challenge
 */
public class AdventOfCode2022 {
    FasterIO io;

    public AdventOfCode2022() {
        io = new FasterIO(System.in, System.out);
    }

    public void run() {
        long lastTime = System.currentTimeMillis();
        io.print(day1());
        io.println("solved in :" + (System.currentTimeMillis()-lastTime) + " ms\n");
        lastTime = System.currentTimeMillis();
        io.print(day2());
        io.println("solved in :" + (System.currentTimeMillis()-lastTime) + " ms\n");
        io.flush();
    }

    /**
     * Implemented a highscore-sorter
     */
    private String day1() {
        io.openData("2022/input01");
        String input;
        int sumCalories = 0;
        int[] maxCalories = new int[3];
        while ((input = io.getLine()) != null) {
            if (input.length() == 0) {
                fastSort(sumCalories, maxCalories);
                sumCalories = 0;
            } else {
                sumCalories += Integer.parseInt(input);
            }
        }
        return "Day 1 part 1: Most calories is " + maxCalories[0] + "\nDay 1 part 2: Sum of three most Calories is " + (maxCalories[0]+maxCalories[1]+maxCalories[2]) + "\n";
    }

    /**
     * Quick insertion into top-three
     */
    private void fastSort(int sum, int[] max) {
        if (sum < max[2])
            return;
        for (int i = 0; i < 2; i++) {
            if (max[i] < sum) {
                insert(max, i, sum);
                break;
            }
        }
    }

    /**
     * Inserts a value into the array at position, discarding the last
     */
    private void insert(int[] list, int position, int value) {
        for (int i = list.length-1 ; i > position ; i--) {
            list[i] = list[i-1];
        }
        list[position] = value;
    }

    /**
     * Managed to construct mathematical alternative to all if-statements
     */
    private String day2() {
        io.openData("2022/input02");
        int score1 = 0;
        int score2 = 0;
        String input;
        while (io.hasMoreTokens()) {
            input = io.getLine();
            int elfMove = input.charAt(0) - 'A';
            int myMove = input.charAt(2) - 'X';
            int outcome = myMove;
            score1 += rockPaperScissors(elfMove, myMove) + (myMove+1);
            score2 += inverseRockPaperScissors(elfMove, outcome) + (outcome*3);
        }
        return "Day 2 part 1: The score is " + score1 + "\nDay 2 part 2: The score is " + score2 + "\n";
    }

    /**
     * Math-style scoresystem for RPS.
     * input is 0, 1 or 2 (R,P,S) and the output is calculated using a pattern identified in the resulttable
     * (p2 wins if the difference between p1 and p2 is 1 mod 3)
     */
    private int rockPaperScissors(int player1, int player2) {
        if (player2==player1)
            return 3;
        if ((player2-player1+3)%3 == 1)
            return 6;
        return 0;
    }

    /**
     * Calculates what move to make to get given outcome. Again this is the result of an analysis of the complete
     * table of combinations
     */
    private int inverseRockPaperScissors(int player1, int outcome) {
        if (outcome == 1)
            return player1+1;
        if (outcome == 0) {
            return (player1-3)%3 + 3;
        }
        return (player1+1)%3 + 1;
    }
}
