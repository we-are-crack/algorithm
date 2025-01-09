import java.io.*;
import java.util.*;

public class Main {

    private static String[] words;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        words = new String[n];
        int[] priority = new int[26];

        for (int i = 0; i < n; i++) {
            words[i] = br.readLine();
            int length = words[i].length();

            for (int j = 0; j < length; j++) {
                priority[words[i].charAt(j) - 'A'] += (int) Math.pow(10, length - j - 1);
            }
        }

        List<Character> alphabetsInWord = new ArrayList<>();

        for (int i = 0; i < 26; i++) {
            if (priority[i] > 0) {
                alphabetsInWord.add((char) (i + 'A'));
            }
        }

        alphabetsInWord.sort((a, b) -> priority[b - 'A'] - priority[a - 'A']);

        int cnt = 0;
        for (char c : alphabetsInWord) {
            priority[c - 'A'] = 9 - cnt++;
        }

        System.out.println(calculate(priority));
    }

    private static int calculate(int[] priority) {
        int total = 0;
        for (String word : words) {
            total += word2Number(word, priority);
        }

        return total;
    }

    private static int word2Number(String word, int[] priority) {
        int num = 0;
        for (int i = 0; i < word.length(); i++) {
            num += (int) (priority[word.charAt(i) - 'A'] * Math.pow(10, word.length() - i - 1));
        }

        return num;
    }
}