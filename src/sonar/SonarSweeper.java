package sonar;

import java.util.ArrayList;

public class SonarSweeper {
    private final ArrayList<Integer> depthVals;

    public SonarSweeper(ArrayList<Integer> inputs) {
        depthVals = inputs;
    }

    public int findNumIncreasingDepths() {
        int increasingDepths = 0;
        for (int i=1; i<depthVals.size();i++) {
            if (depthVals.get(i) > depthVals.get(i-1)) {
                increasingDepths += 1;
            }
        }

        return increasingDepths;
    }

    public int findNumSlidingWindowIncreasingDepths() {
        Integer prevSum = null;
        int increasingDepths = 0;
        for (int i=2; i<depthVals.size();i++) {
            if (prevSum == null) {
                prevSum = depthVals.get(0) + depthVals.get(i-1) + depthVals.get(i);
                continue;
            }

            int currSum = depthVals.get(i-2) + depthVals.get(i-1) + depthVals.get(i);
            if (prevSum < currSum) {
                increasingDepths += 1;
            }

            prevSum = currSum;
        }

        return increasingDepths;
    }
}
