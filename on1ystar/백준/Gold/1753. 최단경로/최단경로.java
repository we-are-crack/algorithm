import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(br.readLine());

        List<List<int[]>> edges = new ArrayList<>();
        for (int i = 0; i <= v; i++) {
            edges.add(new ArrayList<>());
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            edges.get(v1).add(new int[]{v2, weight});
        }

        int[] minWeight = new int[v + 1];
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.offer(new int[] {start, 0});
        while (!pq.isEmpty()) {
            int[] nowVertexAndWeight = pq.poll();

            for (int[] edge : edges.get(nowVertexAndWeight[0])) {
                int nextVertex = edge[0];
                int weight = edge[1];
                int goingNextVertexWeight = minWeight[nowVertexAndWeight[0]] + weight;

                if (minWeight[nextVertex] == 0 || goingNextVertexWeight < minWeight[nextVertex]) {
                    pq.offer(new int[] {nextVertex, goingNextVertexWeight});
                    minWeight[nextVertex] = goingNextVertexWeight;
                }
            }
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 1; i < minWeight.length; i++) {
            if (i == start) {
                bw.write(0 + "\n");
            } else if (minWeight[i] == 0) {
                bw.write("INF" + "\n");
            } else {
                bw.write(minWeight[i] + "\n");
            }
            bw.flush();
        }

        bw.close();
    }
}