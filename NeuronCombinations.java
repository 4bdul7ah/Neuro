import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class NeuronCombinations {

    public static void main(String[] args) {
        int[][] cases = {
                // {36, 6, 1}
                // {49, 7, 1}
                // {64, 8, 1}
                // {81, 9, 1}
                {25, 5, 1}
                // {6, 3, 1}
                // {9, 3, 1}
        };

        for (int[] aCase : cases) {
            int n = aCase[0];
            int s = aCase[1];
            int o = aCase[2];
            System.out.println("For n=" + n + ", s=" + s + ", o=" + o + ":");
            System.out.println("Number of valid sets: " + capacity(n, s, o));
            System.out.println("Valid combinations: " + getValidCombinations(n, s, o));
            System.out.println("--------------------------------------------------");
        }
    }

    public static boolean checkOverlap(Set<Integer> set1, Set<Integer> set2, int maxOverlap) {
        Set<Integer> intersection = new HashSet<>(set1);
        intersection.retainAll(set2);
        return intersection.size() <= maxOverlap;
    }

    public static List<Set<Integer>> getValidCombinations(int neurons, int sets, int overlaps) {
        List<Set<Integer>> allCombinations = new ArrayList<>();
        generateCombinations(allCombinations, new HashSet<>(), 1, neurons, sets);
// tester for brute force
        List<Set<Integer>> initialSets = new ArrayList<>();
        initialSets.add(new HashSet<>(Arrays.asList(1, 7, 9, 11, 13)));
        initialSets.add(new HashSet<>(Arrays.asList(1, 6, 8, 10, 12)));
        initialSets.add(new HashSet<>(Arrays.asList(2, 7, 8, 14, 15)));

        List<Set<Integer>> validCombinations = new ArrayList<>(initialSets);
// test ends here......................
        // List<Set<Integer>> validCombinations = new ArrayList<>();
        for (Set<Integer> combo : allCombinations) {
            boolean isValid = true;
            for (Set<Integer> validSet : validCombinations) {
                if (!checkOverlap(combo, validSet, overlaps)) {
                    isValid = false;
                    break;
                }
            }
            if (isValid) {
                Set<Integer> sortedCombo = new TreeSet<>(combo);
                validCombinations.add(sortedCombo);
                // validCombinations.add(combo);
            }
        }
        System.out.println("Number of valid combinations;" + validCombinations.size());// debugg 

        return validCombinations;
    }

    public static void generateCombinations(List<Set<Integer>> allCombinations, Set<Integer> current, int start, int n, int k) {
        if (k == 0) {
            allCombinations.add(new HashSet<>(current));
            return;
        }

        for (int i = start; i <= n; i++) {
            current.add(i);
            generateCombinations(allCombinations, current, i + 1, n, k - 1);
            current.remove(i);
        }
    }

    public static int capacity(int neurons, int sets, int overlaps) {
        if (neurons == sets * sets && overlaps == 1) {
            return sets * (sets + 1);
        }

        return getValidCombinations(neurons, sets, overlaps).size();
    }
}
