import java.io.*;
import java.util.*;

public class Main {

    private static final Map<String, List<String>> tree = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String parent = st.nextToken();
            tree.put(parent, new ArrayList<>());

            for (int j = 0; j < 2; j++) {
                String child = st.nextToken();
                tree.get(parent).add(child);
            }
        }

        StringBuilder sb = new StringBuilder();
        preOrder("A", sb);
        sb.append("\n");
        inOrder("A", sb);
        sb.append("\n");
        postOrder("A", sb);
        System.out.print(sb);
    }

    private static void preOrder(String parent, StringBuilder sb) {
        if (parent.equals(".")) {
            return;
        }

        sb.append(parent);
        preOrder(tree.get(parent).get(0), sb);
        preOrder(tree.get(parent).get(1), sb);
    }

    private static void postOrder(String parent, StringBuilder sb) {
        if (parent.equals(".")) {
            return;
        }

        postOrder(tree.get(parent).get(0), sb);
        postOrder(tree.get(parent).get(1), sb);
        sb.append(parent);
    }

    private static void inOrder(String parent, StringBuilder sb) {
        if (parent.equals(".")) {
            return;
        }

        inOrder(tree.get(parent).get(0), sb);
        sb.append(parent);
        inOrder(tree.get(parent).get(1), sb);
    }
}