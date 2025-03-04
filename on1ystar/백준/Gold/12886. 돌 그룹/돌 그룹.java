import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] initGroups = new int[3];
        for (int i = 0; i < 3; i++) {
            initGroups[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(initGroups);

        Deque<int[]> q = new ArrayDeque<>();
        q.add(initGroups);
        boolean[][] visited = new boolean[1501][1501];
        visited[initGroups[0]][initGroups[1]] = true;
        while (!q.isEmpty()) {
            int[] groups = q.removeFirst();

            if (groups[0] == groups[1] && groups[1] == groups[2]) {
                System.out.println(1);
                return;
            }

            for (int i = 0; i < 2; i++) {
                for (int j = i + 1; j < 3; j++) {
                    int[] newGroups = groups.clone();

                    if (groups[i] > groups[j]) {
                        newGroups[i] -= newGroups[j];
                        newGroups[j] += newGroups[j];
                    } else if (groups[i] < groups[j]) {
                        newGroups[j] -= newGroups[i];
                        newGroups[i] += newGroups[i];
                    } else continue;

                    Arrays.sort(newGroups);

                    if(!visited[newGroups[0]][newGroups[1]]) {
                        visited[newGroups[0]][newGroups[1]] = true;
                        q.addLast(newGroups);
                    }
                }
            }
        }

        System.out.println(0);
    }
}