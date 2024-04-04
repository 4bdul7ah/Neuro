import java.util.*;

public class NeuronCombinations {

    public static void main(String[] args) {
        int[][] cases = {
                {9, 3, 1} // Example test case should have 12 valid sets and print out all the 12 valid sets
        };

        for (int[] aCase : cases) {
            int n = aCase[0];
            int s = aCase[1];
            int o = aCase[2];
            System.out.println("For n=" + n + ", s=" + s + ", o=" + o + ":");
            List<Set<Integer>> validCombinations = getValidCombinations(n, s, o);
            System.out.println("Number of valid sets: " + validCombinations.size());
            System.out.println("Valid combinations: " + validCombinations);
            System.out.println("--------------------------------------------------");
        }
    }

    private static List<Set<Integer>> getValidCombinations(int neurons, int sets, int overlaps) {
        List<Set<Integer>> validCombinations = new ArrayList<>();
        backtrack(validCombinations, new ArrayList<>(), 1, neurons, sets, overlaps);
        return validCombinations;
    }

    private static void backtrack(List<Set<Integer>> validCombinations, List<Integer> current, int start, int neurons, int sets, int overlaps) {
        if (current.size() == sets) {
            if (isValidCombination(validCombinations, current, overlaps)) {
                validCombinations.add(new HashSet<>(current));
            }
            return;
        }

        for (int i = start; i <= neurons; i++) {
            current.add(i);
            backtrack(validCombinations, current, i + 1, neurons, sets, overlaps);
            current.remove(current.size() - 1);
        }
    }

    private static boolean isValidCombination(List<Set<Integer>> validCombinations, List<Integer> current, int overlaps) {
        for (Set<Integer> validCombination : validCombinations) {
            int count = 0;
            for (int num : current) {
                if (validCombination.contains(num)) {
                    count++;
                }
            }
            if (count > overlaps) {
                return false;
            }
        }
        return true;
    }
}