import java.io.*;
import java.util.*;

public class Main {

    private static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        boolean answer = true;

        parent = new int[n + 1];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }

        for (int node1 = 1; node1 <= n; node1++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int node2 = 1; node2 <= n; node2++) {
                boolean isConnected = Integer.parseInt(st.nextToken()) == 1;
                if (isConnected) {
                    union(node1, node2);
                }
            }
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int p = parent[Integer.parseInt(st.nextToken())];
        for (int i = 1; i < m; i++) {
            int node = Integer.parseInt(st.nextToken());

            if (p != parent[node]) {
                answer = false;
                break;
            }
        }

        System.out.println(answer ? "YES" : "NO");
    }

    private static void union(int node1, int node2) {
        int p1 = findParent(node1);
        int p2 = findParent(node2);

        if (p1 != p2) {
            if (p1 > p2) parent[p1] = p2;
            else parent[p2] = p1;
        }
    }

    private static int findParent(int node) {
        if (parent[node] == node) return node;
        return parent[node] = findParent(parent[node]);
    }
}
