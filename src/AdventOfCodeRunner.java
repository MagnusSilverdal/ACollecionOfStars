/**
 * Created by magnus on 2022-12-06.
 */
public class AdventOfCodeRunner {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        long totalStartTime = startTime;
        System.out.println("Advent of code 2022");
        AdventOfCode2022 AoC2022 = new AdventOfCode2022();
        startTime = System.currentTimeMillis();
        AoC2022.run();
        System.out.println("Total time 2022: " + (System.currentTimeMillis() - startTime));
        System.out.println("Total number of stars: 12");
    }
}
