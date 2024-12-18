import java.io.*;
import java.util.*;

public class Main {

    private static final int MAX_LENGTH = 10_001, MIN_LENGTH = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        StringBuilder answer = new StringBuilder();
        while (t-- > 0) {
            int[] result = solution(br.readLine(), Integer.parseInt(br.readLine()));
            if (result == null) {
                answer.append(-1);
            } else {
                answer.append(result[0]).append(" ").append(result[1]);
            }
            answer.append("\n");
        }

        System.out.print(answer);
    }

    private static int[] solution(String str, int k) {
        Map<Character, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            List<Integer> indexes = map.getOrDefault(str.charAt(i), new ArrayList<>());
            indexes.add(i);
            map.put(str.charAt(i), indexes);
        }

        int min = MAX_LENGTH;
        int max = MIN_LENGTH;
        for (Map.Entry<Character, List<Integer>> entry : map.entrySet()) {
            List<Integer> indexes = entry.getValue();
            for (int i = 0; i <= indexes.size() - k; i++) {
                min = Math.min(min, indexes.get(i + k - 1) - indexes.get(i) + 1);
                max = Math.max(max, indexes.get(i + k - 1) - indexes.get(i) + 1);
            }
        }

        if (min == MAX_LENGTH || max == MIN_LENGTH) {
            return null;
        } else return new int[]{min, max};
    }
}
