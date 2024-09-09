import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {

    private static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        int edgeCount = 0;
        int mstWeight = 0;

        parents = new int[v + 1];
        for (int i = 1; i <= v; i++) {
            parents[i] = i;
        }

        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int edge = Integer.parseInt(st.nextToken());
            edges.add(new Edge(v1, v2, edge));
        }

        Collections.sort(edges, Comparator.comparingInt(e2 -> e2.weight));

        for (int i = 0; i < edges.size(); i++) {
            if (edges.get(i).unionParent()) {
                mstWeight += edges.get(i).weight;

                if (++edgeCount == v- 1) {
                    System.out.println(mstWeight);
                }
            }
        }
    }

    private static class Edge {

        int v1;
        int v2;
        int weight;

        public Edge(int v1, int v2, int weight) {
            this.v1 = v1;
            this.v2 = v2;
            this.weight = weight;
        }

        private int findParent(int v) {
            if (parents[v] == v) {
                return v;
            } else {
                parents[v] = findParent(parents[v]);
            }

            return parents[v];
        }

        private boolean unionParent() {
            int v1Parent = findParent(v1);
            int v2Parent = findParent(v2);

            if (v1Parent == v2Parent) {
                return false;
            }

            if (v1Parent > v2Parent) {
                parents[v1Parent] = v2Parent;
            } else {
                parents[v2Parent] = v1Parent;
            }

            return true;
        }
    }
}