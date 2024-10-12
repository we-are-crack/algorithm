import java.io.*;
import java.util.*;

public class Main {

    private static int n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        List<List<Edge>> cities = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            cities.add(new ArrayList<>());
        }

        StringTokenizer st;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int dpt = Integer.parseInt(st.nextToken());
            int arv = Integer.parseInt(st.nextToken());
            int cst = Integer.parseInt(st.nextToken());
            cities.get(dpt).add(new Edge(arv, cst));
        }

        st = new StringTokenizer(br.readLine());
        int departure = Integer.parseInt(st.nextToken());
        int arrival = Integer.parseInt(st.nextToken());

        dijkstra(departure, arrival, cities);
    }

    private static void dijkstra(int departure, int arrival, List<List<Edge>> cities) {
        int[] minCostFromDeparture = new int[n + 1];
        Arrays.fill(minCostFromDeparture, Integer.MAX_VALUE);

        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(Edge::getCost));
        pq.offer(new Edge(departure, 0));
        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            int fromCity = edge.getCity();
            int minCost = edge.getCost();

            if (fromCity == arrival) {
                System.out.println(minCost);
                break;
            }

            if (minCostFromDeparture[fromCity] < minCost) {
                continue;
            }

            for (Edge e : cities.get(fromCity)) {
                int toCity = e.getCity();
                int costFromCityToCity = e.getCost();

                if (minCostFromDeparture[toCity] > minCost + costFromCityToCity) {
                    minCostFromDeparture[toCity] = minCost + costFromCityToCity;
                    pq.offer(new Edge(toCity, minCostFromDeparture[toCity]));
                }
            }
        }
    }

    private static class Edge {

        private final int city;
        private final int cost;

        public Edge(int city, int cost) {
            this.city = city;
            this.cost = cost;
        }

        public int getCity() {
            return city;
        }

        public int getCost() {
            return cost;
        }
    }
}