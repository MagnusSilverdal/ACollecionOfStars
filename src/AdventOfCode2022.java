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
        io.flush();
    }

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
        for (int i = 0 ; i < 2 ; i++) {
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
}
