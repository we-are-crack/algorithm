import java.io.*;
import java.util.*;

public class Main {

    private static int maxSamePrefixLength = 0;
    private static String S, T;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Set<String> wordSet = new LinkedHashSet<>(n);
        for (int i = 0; i < n; i++) {
            wordSet.add(br.readLine());
        }

        List<String> wordIndexList = new ArrayList<>(wordSet);

        findSamePrefix(wordIndexList, 0);

        System.out.print(S + "\n" + T);
    }

    private static void findSamePrefix(List<String> wordList, int samePrefixLength) {
        Map<Character, List<String>> sameAlphabetWordMap = new LinkedHashMap<>();

        if (samePrefixLength > maxSamePrefixLength) {
            maxSamePrefixLength = samePrefixLength;
            S = wordList.get(0);
            T = wordList.get(1);
        }

        for (String word : wordList) {
            if (word.length() > samePrefixLength) {
                char alphabet = word.charAt(samePrefixLength);
                sameAlphabetWordMap.computeIfAbsent(alphabet, k -> new ArrayList<>()).add(word);
            }
        }

        for (List<String> nextWordList : sameAlphabetWordMap.values()) {
            if (nextWordList.size() > 1) {
                findSamePrefix(nextWordList, samePrefixLength + 1);
            }
        }
    }
}