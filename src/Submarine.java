import helpers.IntegerInputScanner;
import helpers.StringInputScanner;

import java.io.File;

public class Submarine {
    public static void main(String[] args) {
        System.out.println("Our submarine adventure begins");

        System.out.println("Day 1");
        IntegerInputScanner integerInputScanner = new IntegerInputScanner(new File("resources/day 1/input.txt"));
        SonarSweeper sonarSweeper = new SonarSweeper(integerInputScanner.getResult());
        System.out.println(String.format("Number of increasing depths: %d", sonarSweeper.findNumIncreasingDepths()));
        System.out.println(String.format("Number of increasing depths in 3-measurement sliding window: %d",
                sonarSweeper.findNumSlidingWindowIncreasingDepths() ));


        System.out.println("Day 4");

        StringInputScanner inputScanner = new StringInputScanner(new File("resources/day 4/input.txt"));
        System.out.println("let's play bingo!");
        Bingo bingo = new Bingo(inputScanner.getResult());
        System.out.println(String.format("First winning board score: %d",bingo.findFirstWinningBoardScore()));
        System.out.println(String.format("Last winning board score: %d",bingo.findLastWinningBoardScore()));
    }
}
