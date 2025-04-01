import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Main {

    private static int n;
    private static int[] people;
    private static boolean[][] graph;
    private static int fullGraphBit;
    private static int minDiff;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        people = new int[n + 1];
        fullGraphBit = (1 << n) - 1;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            people[i] = Integer.parseInt(st.nextToken());
        }

        graph = new boolean[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            graph[0][i] = true;
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());

            for (int j = 0; j < cnt; j++) {
                graph[i][Integer.parseInt(st.nextToken())] = true;
            }
        }

//        int sumPeople = isConnected(1 ^ fullGraphBit);
//        if (sumPeople == 0) {
//            minDiff = 1001;
//        } else {
//            minDiff = Math.abs(sumPeople - people[1]);
//        }

        minDiff = 1001;

        comb(1, 0, 0);

        if (minDiff == 1001) {
            System.out.println(-1);
        } else {
            System.out.println(minDiff);
        }
    }


    private static int isConnected(int subGraphBit) {
        Deque<Integer> q = new ArrayDeque<>();
        int visitedBit = 0;
        int sumPeople = 0;

        for (int i = 1; i <= n; i++) {
            int nodeBit = 1 << (i - 1);
            if ((subGraphBit & nodeBit) == nodeBit) {
                q.add(i);
                visitedBit += nodeBit;
                sumPeople += people[i];
                break;
            }
        }

        while (!q.isEmpty()) {
            int node = q.removeFirst();

            for (int nextNode = 1; nextNode <= n; nextNode++) {
                int nextNodeBit = 1 << (nextNode - 1);
                if (graph[node][nextNode] && (subGraphBit & nextNodeBit) == nextNodeBit && (visitedBit & nextNodeBit) == 0) {
                    visitedBit += nextNodeBit;
                    q.addLast(nextNode);
                    sumPeople += people[nextNode];
                }
            }
        }

        if (subGraphBit == visitedBit) {
            return sumPeople;
        }
        return 0;
    }

    private static void comb(int start, int subGraphBit, int size) {
        if (size == n - 1) {
            return;
        }

        for (int nextNode = start; nextNode <= n; nextNode++) {
            int nextNodeBit = 1 << (nextNode - 1);
            if ((subGraphBit & nextNodeBit) == 0) {
                subGraphBit += nextNodeBit;
                int sumPeopleA = isConnected(subGraphBit);

                if (sumPeopleA != 0) {
                    int sumPeopleB = isConnected(subGraphBit ^ fullGraphBit);

                    if (sumPeopleB != 0) {
                        minDiff = Math.min(minDiff, Math.abs(sumPeopleA - sumPeopleB));
                    }
                }

                comb(nextNode + 1, subGraphBit, size + 1);
                subGraphBit -= nextNodeBit;
            }
        }
    }
}