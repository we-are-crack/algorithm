import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < t; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            sb.append(bfs(a, b));
            sb.append("\n");
        }

        System.out.print(sb);
    }

    private static String bfs(int a, int b) {
        String result = "";
        Deque<Node> q = new ArrayDeque<>();
        boolean[] visited = new boolean[10_001];
        char[] DSLR = new char[]{'D', 'S', 'L', 'R'};

        q.addLast(new Node(a, ""));
        visited[a] = true;

        while (!q.isEmpty()) {
            Node node = q.removeFirst();

            if (node.num == b) {
                result = node.ops.toString();
                break;
            }

            for (char op : DSLR) {
                int operatedNum;

                if (op == 'D') {
                    operatedNum = operateD(node.num);
                } else if (op == 'S') {
                    operatedNum = operateS(node.num);
                } else if (op == 'L') {
                    operatedNum = operateL(node.num);
                } else {
                    operatedNum = operateR(node.num);
                }

                if (!visited[operatedNum]) {
                    visited[operatedNum] = true;
                    Node operatedNode = new Node(operatedNum, node.ops.toString());
                    operatedNode.addOp(op);
                    q.addLast(operatedNode);
                }
            }
        }

        return result;
    }

    private static int operateD(int num) {
        return (num * 2) % 10_000;
    }

    private static int operateS(int num) {
        if (num == 0) {
            num = 10_000;
        }
        return num - 1;
    }

    private static int operateL(int num) {
        return (num % 1000) * 10 + (num / 1000);
    }

    private static int operateR(int num) {
        return (num % 10) * 1000 + (num / 10);
    }

    static class Node {
        private final int num;
        private final StringBuilder ops = new StringBuilder();

        public Node(int num, String ops) {
            this.num = num;
            this.ops.append(ops);
        }

        public void addOp(char op) {
            ops.append(op);
        }
    }
}