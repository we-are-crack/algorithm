import java.io.*;
import java.util.*;

public class Main {

    private static int n, m, x;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());

        List<List<int[]>> graph = new ArrayList<>();
        List<List<int[]>> reversedGraph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
            reversedGraph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph.get(from).add(new int[]{to, cost});
            reversedGraph.get(to).add(new int[]{from, cost});
        }

        int[] goPartyCost = goParty(graph);
        int[] comebackHomeCost = goParty(reversedGraph);

        int maxCost = 0;
        for (int i = 1; i <= n; i++) {
            maxCost = Math.max(maxCost, goPartyCost[i] + comebackHomeCost[i]);
        }

        System.out.println(maxCost);
    }

    private static int[] goParty(List<List<int[]>> graph) {
        int[] minCost = new int[n + 1];
        Arrays.fill(minCost, Integer.MAX_VALUE);
        minCost[x] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.add(new int[]{x, 0});
        
        while (!pq.isEmpty()) {
            int[] edge = pq.poll();

            if (minCost[edge[0]] < edge[1]) {
                continue;
            }

            for (int[] nextEdge : graph.get(edge[0])) {
                int nextTotalCost = edge[1] + nextEdge[1];
                if (minCost[nextEdge[0]] <= nextTotalCost) {
                    continue;
                }

                minCost[nextEdge[0]] = nextTotalCost;
                pq.add(new int[]{nextEdge[0], nextTotalCost});
            }
        }

        return minCost;
    }
}