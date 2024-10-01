import java.io.*;
import java.util.*;

public class Main {

    private static int n, m;
    private static List<Integer> numbers;
    private static final HashSet<Sequence> seqSet = new HashSet<>();
    private static final StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        numbers = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int number = Integer.parseInt(st.nextToken());
            if (!numbers.contains(number)) {
               numbers.add(number);
            }
        }

        Collections.sort(numbers);
        dfs(new Sequence(m), 0);
        System.out.print(sb);

    }

    private static void dfs(Sequence seq, int j) {
        if (seq.size() == m) {
            if (seqSet.add(seq)) {
                sb.append(seq).append("\n");
            }
            return;
        }

        for (int i = j; i < numbers.size(); i++) {
            Sequence newSeq = new Sequence(seq);
            newSeq.add(numbers.get(i));
            dfs(newSeq, i);
        }
    }

    static class Sequence {

        private final int[] arr;
        private int index;

        public Sequence(int size) {
            arr = new int[size];
            index = 0;
        }

        public Sequence(Sequence seq) {
            arr = Arrays.copyOf(seq.getArr(), seq.getArr().length);
            index = seq.getIndex();
        }

        public int[] getArr() {
            return arr;
        }

        public int getIndex() {
            return index;
        }

        public void add(int val) {
            arr[index++] = val;
        }

        public int size() {
            return index;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }

            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            Sequence sequence = (Sequence) o;
            return Objects.deepEquals(arr, sequence.getArr());
        }

        @Override
        public int hashCode() {
            return Objects.hash(Arrays.hashCode(arr));
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            for (int e : arr) {
                builder.append(e).append(" ");
            }

            return builder.toString();
        }
    }
}