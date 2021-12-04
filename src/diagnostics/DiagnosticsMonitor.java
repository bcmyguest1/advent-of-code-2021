package diagnostics;

import helpers.BitManipulation;

import java.util.ArrayList;
import java.util.Collections;

import static java.lang.Math.abs;

public class DiagnosticsMonitor {
    private final int numBits;
    private ArrayList<Integer> metrics;
    private Integer gammaRate = 0;
    private Integer epsilonRate = 0;
    private Integer powerConsumption;
    private ArrayList<Integer> countBits;
    private Integer oxygenGeneratorRating;
    private Integer co2ScrubberRating;

    public DiagnosticsMonitor(ArrayList<Integer> metrics, int numBits) {
        this.metrics = metrics;
        this.numBits = numBits;
        countBits = new ArrayList<Integer>(Collections.nCopies(numBits, 0));
    }

    public Integer determinePowerConsumtion() {
        for (Integer metric: metrics) {
            for (int i=0; i<numBits;i++) {
                int count = countBits.get(i);
                countBits.set(i, count + BitManipulation.getPosition(metric, i));
            }
        }

        for (int i=0; i<countBits.size();i++) {
            // if more than half of the bits are set, most common value for that bit is set
            if (countBits.get(i) > (metrics.size() / 2)) {
                gammaRate = BitManipulation.setBit(gammaRate, i);
            }
        }

        epsilonRate = gammaRate;

        for (int i=0; i< numBits;i++) {
            epsilonRate = BitManipulation.flipBit(epsilonRate, i);
        }

        System.out.println(String.format("gamma: %d - epsilon: %d", gammaRate, epsilonRate));
        System.out.println(String.format("gamma bit rep: %s - epsilon bit rep: %s",
                Integer.toBinaryString(gammaRate), Integer.toBinaryString(epsilonRate)));
        powerConsumption = epsilonRate * gammaRate;

        return powerConsumption;
    }

    public Integer determineLifeSupportRating() {
        this.determinePowerConsumtion();

        ArrayList<Integer> metricsToCheck = metrics;
        ArrayList<Integer> metricsToCheckNext = new ArrayList<>();
        for (int i=numBits-1; i>=0; i--) {
            for (Integer metric: metricsToCheck) {
//              it would be faster if we didn't abstract the bit manipulation but I find this more readable
                if (BitManipulation.getPosition(metric, i).equals(BitManipulation.getPosition(gammaRate, i))) {
                    metricsToCheckNext.add(metric);
                }
            }

            if (metricsToCheckNext.size() == 1) {
                oxygenGeneratorRating = metricsToCheckNext.get(0);
                break;
            }

            metricsToCheck = new ArrayList<>(metricsToCheckNext);
            metricsToCheckNext.clear();
        }

        metricsToCheck = metrics;
        metricsToCheckNext.clear();

        for (int i=numBits-1; i>=0; i--) {
            for (Integer metric: metricsToCheck) {
                if (!BitManipulation.getPosition(metric, i).equals(BitManipulation.getPosition(gammaRate, i))) {
                    metricsToCheckNext.add(metric);
                }
            }

            if (metricsToCheckNext.size() == 1) {
                co2ScrubberRating = metricsToCheckNext.get(0);
                break;
            }

            metricsToCheck = new ArrayList<>(metricsToCheckNext);
            metricsToCheckNext.clear();
        }

        System.out.println(String.format("oxygen bit rep: %s - co2 bit rep: %s",
                Integer.toBinaryString(oxygenGeneratorRating), Integer.toBinaryString(co2ScrubberRating)));
        System.out.println(String.format("oxygen: %d - co2: %d", oxygenGeneratorRating, co2ScrubberRating));


        return co2ScrubberRating * oxygenGeneratorRating;
    }
}
