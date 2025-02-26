import java.io.*;
import java.util.*;

public class Main {

    private static int[][] country;
    private static int n, l, r;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        country = new int[n][n];
        for (int y = 0; y < n; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < n; x++) {
                country[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;
        while (true) {
            boolean flag = false;
            boolean[][] visit = new boolean[n][n];
            for (int y = 0; y < n; y++) {
                for (int x = 0; x < n; x++) {
                    if (visit[y][x]) {
                        continue;
                    }

                    Association association = new Association();
                    unite(association, visit, y, x);

                    if (association.getJoinedCountryCount() > 1) {
                        flag = true;
                        association.move();
                    }
                }
            }
            
            if (!flag) {
                break;
            }

            answer++;
        }
        
        System.out.println(answer);
    }

    private static void unite(Association association, boolean[][] visit, int y, int x) {
        association.join(y, x);
        visit[y][x] = true;

        int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        for (int[] d : dir) {
            int ny = y + d[0];
            int nx = x + d[1];

            if (!checkRange(ny ,nx) || visit[ny][nx]) {
                continue;
            }

            int diff = Math.abs(country[y][x] - country[ny][nx]);
            if (l <= diff && diff <= r) {
                unite(association, visit, ny, nx);
            }
        }
    }

    private static boolean checkRange(int y, int x) {
        return 0 <= y && y < n && 0 <= x && x < n;
    }

    private static class Association {

        private List<int[]> joinedCountry = new ArrayList<>();
        private int population;

        public void join(int y, int x) {
            joinedCountry.add(new int[] {y, x});
            population += country[y][x];
        }

        public void move() {
            int avg = population / joinedCountry.size();
            for (int[] jc : joinedCountry) {
                int y = jc[0], x = jc[1];
                country[y][x] = avg;
            }
        }

        public int getJoinedCountryCount() {
            return joinedCountry.size();
        }
    }
}
