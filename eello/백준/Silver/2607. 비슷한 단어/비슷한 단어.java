import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Word word = new Word(br.readLine());

        int answer = 0;
        for (int i = 1; i < n; i++) {
            Word target = new Word(br.readLine());
            if (word.isSimilar(target)) {
                answer++;
            }
        }

        System.out.println(answer);
    }

    private static class Word {
        private String word;
        private Map<Character, Integer> composition = new HashMap<>();

        public Word(String word) {
            this.word = word;
            for (char ch : word.toCharArray()) {
                composition.put(ch, composition.getOrDefault(ch, 0) + 1);
            }
        }

        public boolean isSimilar(Word other) {
            if (word.equals(other.word)) {
                return true;
            }

            Set<Character> allChars = new HashSet<>();
            allChars.addAll(composition.keySet());
            allChars.addAll(other.composition.keySet());

            int diff = 0;
            for (Character key : allChars) {
                diff += Math.abs(composition.getOrDefault(key, 0) - other.composition.getOrDefault(key, 0));
            }

            if (diff < 2) {
                return true;
            }

            return word.length() == other.word.length() && diff == 2;
        }
    }
}
