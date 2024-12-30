import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int m = Integer.parseInt(br.readLine());

        S s = new S();
        StringBuilder answer = new StringBuilder();
        while (m-- > 0) {
            StringTokenizer command = new StringTokenizer(br.readLine());

            switch (command.nextToken()) {
                case "add":
                    s.add(Integer.parseInt(command.nextToken()));
                    break;
                case "remove":
                    s.remove(Integer.parseInt(command.nextToken()));
                    break;
                case "check":
                    answer.append(s.check(Integer.parseInt(command.nextToken()))).append("\n");
                    break;
                case "toggle":
                    s.toggle(Integer.parseInt(command.nextToken()));
                    break;
                case "all":
                    s.all();
                    break;
                case "empty":
                    s.empty();
                    break;
                default:
                    throw new IllegalArgumentException();
            }
        }

        System.out.println(answer);
    }

    static class S {
        private boolean[] set = new boolean[21];

        public void add(int x) {
            set[x] = true;
        }

        public void remove(int x) {
            set[x] = false;
        }

        public int check(int x) {
            return set[x] ? 1 : 0;
        }

        public void toggle(int x) {
            set[x] = !set[x];
        }

        public void all() {
            Arrays.fill(set, true);
        }

        public void empty() {
            Arrays.fill(set, false);
        }
    }
}
