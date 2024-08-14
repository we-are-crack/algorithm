import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        int start = Integer.parseInt(line[0]);
        int dest = Integer.parseInt(line[1]);
        int[] graph = new int[100_001];
        Arrays.fill(graph, -1);
        Queue<Integer> q = new LinkedList<>();

        graph[start] = start;
        q.add(start);
        int second = -1;
        boolean isArrived = false;
        while(!isArrived) {
            second++;
            int queueSize = q.size();
            while(queueSize > 0) {
                int cur = q.poll();
                if(cur == dest) {
                    isArrived = true;
                    break;
                }
                for(int m : move(cur)) {
                    if(0 > m || 100_000 < m || graph[m] != -1) continue;
                    graph[m] = cur;
                    q.offer(m);
                }
                queueSize--;
            }
        }
        int[] path = new int[second + 1];
        path[second] = dest;
        for(int i = second; i > 0; i--) path[i - 1] = graph[path[i]];
        System.out.println(second);
        for(int i = 0; i < path.length; i++) System.out.print(path[i] + " ");
    }
    private static int[] move(int pos) {
        return new int[]{pos - 1, pos + 1, pos * 2};
    }
 }