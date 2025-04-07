import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        StringBuilder answer = new StringBuilder();
        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int target = Integer.parseInt(st.nextToken());

            answer.append(bfs(start, target)).append("\n");
        }

        System.out.print(answer);
    }


    private static String bfs(int start, int target) {
        Queue<Number> q = new ArrayDeque<>();
        BitSet visit = new BitSet();

        q.add(new Number(start));
        visit.set(start);

        char[] operations = {'D', 'S', 'L', 'R'};
        while (!q.isEmpty()) {
            Number cur = q.poll();

            if (cur.num == target) {
                return cur.operations;
            }

            for (char op : operations) {
                Number next = operate(cur, op);

                if (visit.get(next.num)) {
                    continue;
                }

                visit.set(next.num);
                q.add(next);
            }
        }

        return null;
    }

    private static Number operate(Number origin, char op) {
        Number clone = origin.clone();
        switch (op) {
            case 'D':
                clone.D();
                break;
            case 'S':
                clone.S();
                break;
            case 'L':
                clone.L();
                break;
            case 'R':
                clone.R();
                break;
        }

        return clone;
    }

    private static class Number {
        private int num;
        private String operations = "";

        public Number(int num) {
            this.num = num;
        }

        public Number(int num, String operations) {
            this.num = num;
            this.operations = operations;
        }

        public void D() {
            num = (num * 2) % 10_000;
            operations += 'D';
        }

        public void S() {
            num = num - 1 < 0 ? 9999 : num - 1;
            operations += 'S';
        }

        public void L() {
            int temp = num / 1_000;
            num %= 1_000;
            num *= 10;
            num += temp;
            operations += 'L';
        }

        public void R() {
            int temp = num % 10;
            num /= 10;
            num += temp * 1_000;
            operations += 'R';
        }

        public Number clone() {
            return new Number(num, operations);
        }
    }
}
