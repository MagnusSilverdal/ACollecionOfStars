import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
        lastTime = System.currentTimeMillis();
        io.print(day3());
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
     * Quick insertion into fixed-size array
     */
    private void fastSort(int sum, int[] max) {
        if (sum < max[max.length-1])
            return;
        for (int i = 0; i < max.length-1 ; i++) {
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

    /**
     * Combined and refactored solutions. Using methods to clarify.
     */
    private String day3() {
        io.openData("2022/input03");
        String[] input = new String[3];
        int score1 = 0;
        int score2 = 0;
        while(io.hasMoreTokens()) {
            for (int i = 0 ; i < 3 ; i++) {
                input[i] = io.getLine();
                score1 += getScore(findDuplicate(input[i]));
            }
            score2 += getScore(findBadge(input));
        }
        return "Day 3 part 1: The sum of priorities is " + score1 + "\nDay 3 part 2: The sum of priorities is " + score2 + "\n";
    }

    /**
     * Split string and loop through halves looking for a duplicate.
     * Tried a set solution with intersection but it was slower
     */
    private char findDuplicate(String rucksack) {
        String secondCompartment = rucksack.substring(rucksack.length()/2);
        for (int i = 0 ; i < rucksack.length()/2 ; i++) {
            if (secondCompartment.contains(""+rucksack.charAt(i))) {
                return rucksack.charAt(i);
            }
        }
        return ' ';
    }

    /**
     * Find a letter that is contained in all three rucksacks
     */
    private char findBadge(String[] rucksacks) {
        for (int i = 0 ; i < rucksacks[0].length() ; i++) {
            char letterToCheck = rucksacks[0].charAt(i);
            if (rucksacks[1].contains(""+letterToCheck) && rucksacks[2].contains(""+letterToCheck)) {
                return letterToCheck;
            }
        }
        return ' ';
    }

    /**
     * Calculates the score from each package
     */
    private int getScore(char c) {
        if (c < 'a') {
            return c -'A' +27;
        } else {
            return c - 'a' +1;
        }
    }


}
