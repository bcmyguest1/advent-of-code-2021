package navigation;

import helpers.Pair;

import java.util.ArrayList;

public class LocationAnalyzer {
    public ArrayList<Pair<String, Integer>> actions = new ArrayList<>();

    public LocationAnalyzer(ArrayList<String> inputActions) {
        for (String action: inputActions) {
            String[] split = action.split(" ");
            actions.add(new Pair<String, Integer>(split[0].toLowerCase(), Integer.parseInt(split[1])));
        }
    }

    public Location findFinalLocationWithAim() {
        int depth = 0;
        int position = 0;
        int aim = 0;
        for (Pair<String, Integer> action: actions) {
            switch (action.getKey()) {
                case "forward":
                    position += action.getValue();
                    depth += action.getValue() * aim;
                    break;
                case "up":
                    aim -= action.getValue();
                    break;
                case "down":
                    aim += action.getValue();
                    break;
            }
        }

        return new Location(depth, position);
    }

    public Location findFinalLocation() {
        int depth = 0;
        int position = 0;
        for (Pair<String, Integer> action: actions) {
            switch (action.getKey()) {
                case "forward":
                    position += action.getValue();
                    break;
                case "up":
                    depth -= action.getValue();
                    break;
                case "down":
                    depth += action.getValue();
                    break;
            }
        }

        return new Location(depth, position);
    }
}
