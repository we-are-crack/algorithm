import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Map<String, Word> words = new HashMap<>();
        while (n-- > 0) {
            String w = br.readLine();
            if (w.length() < m) {
                continue;
            }

            Word word = words.computeIfAbsent(w, Word::new);
            word.appear();
        }

        List<Word> voca = new ArrayList<>(words.values());
        Collections.sort(voca);

        for (Word word : voca) {
            bw.append(word.getWord()).append("\n");
        }

        bw.flush();
    }

    private static class Word implements Comparable<Word> {
        private String word;
        private int count; // 등장횟수

        public Word(String word) {
            this.word = word;
        }

        public void appear() {
            this.count++;
        }

        public String getWord() {
            return word;
        }

        @Override
        public int compareTo(Word o) {
            if (this.count != o.count) {
                return o.count - this.count;
            }

            if (this.word.length() != o.word.length()) {
                return o.word.length() - this.word.length();
            }

            return word.compareTo(o.word);
        }
    }
}
