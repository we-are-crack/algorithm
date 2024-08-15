import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        int cities = Integer.parseInt(line[0]);
        int roads = Integer.parseInt(line[1]);
        int shortestPath = Integer.parseInt(line[2]);
        int start = Integer.parseInt(line[3]);

        ArrayList<Integer> answer = new ArrayList<>();
        int[] distance = new int[300_001];
        Queue<Integer> q = new LinkedList<>();
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>(300_001);
        for(int i = 0; i < cities + 1; i++) graph.add(new ArrayList<>());
        for(int i = 0; i < roads; i++) {
            String[] road = br.readLine().split(" ");
            graph.get(Integer.parseInt(road[0])).add(Integer.parseInt(road[1]));
        }
        q.offer(start);
        while(!q.isEmpty()) {
            int now = q.poll();
            if(distance[now] > shortestPath) break;
            for(int next : graph.get(now)) {
                if(distance[next] == 0 && next != start) {
                    q.offer(next);
                    distance[next] = distance[now] + 1;
                    if(distance[next] == shortestPath) answer.add(next);
                }
            }
        }
        if(answer.size() == 0) System.out.println(-1);
        Collections.sort(answer);
        for(int i = 0; i < answer.size(); i++) {
            System.out.println(answer.get(i));
        }
    }
 }