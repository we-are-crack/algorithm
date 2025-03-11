import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;


public class Main {

    private static int w, h;
    private static final int[] dr = {1, 0, -1, 0};
    private static final int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        String[] myMap = new String[h];
        List<Pos> cList = new ArrayList<>();
        for (int i = 0; i < h; i++) {
            myMap[i] = br.readLine();  // 길이가 w

            if (cList.size() != 2) {
                for (int j = 0; j < w; j++) {
                    if (myMap[i].charAt(j) == 'C') {
                        cList.add(new Pos(i, j, -1));
                    }
                }
            }
        }

        Pos start = cList.get(0);
        Pos target = cList.get(1);
        PriorityQueue<Pos> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.mirrorCnt));
        int[][][] visited = new int[h][w][4];  // visited[r][c][4] = 최소 거울 수

        for (int i = 0; i < 4; i++) {
            visited[start.r][start.c][i] = -1;
        }

        pq.add(start);
        while (!pq.isEmpty()) {
            Pos nowPos = pq.remove();

            if (nowPos.r == target.r && nowPos.c == target.c) {
                System.out.println(nowPos.mirrorCnt - 1);
                break;
            }

            for (int nextDirection = 0; nextDirection < 4; nextDirection++) {
                Pos nextPos = new Pos(nowPos.r + dr[nextDirection], nowPos.c + dc[nextDirection], nextDirection);

                if (!nextPos.isValid() || myMap[nextPos.r].charAt(nextPos.c) == '*') {
                    continue;
                }

                if (nowPos.direction != nextPos.direction) {
                    nextPos.mirrorCnt = nowPos.mirrorCnt + 1;
                } else {
                    nextPos.mirrorCnt = nowPos.mirrorCnt;
                }

                if (visited[nextPos.r][nextPos.c][nextPos.direction] == 0 || nextPos.mirrorCnt < visited[nextPos.r][nextPos.c][nextPos.direction]) {
                    visited[nextPos.r][nextPos.c][nextPos.direction] = nextPos.mirrorCnt;
                    pq.add(nextPos);
                }
            }
        }
    }

    static class Pos {
        private final int r;
        private final int c;
        private final int direction;  // 방향
        private int mirrorCnt = 0;

        public Pos(int r, int c, int direction) {
            this.r = r;
            this.c = c;
            this.direction = direction;
        }

        public boolean isValid() {
            return 0 <= r && r < h && 0 <= c && c < w;
        }
    }
}