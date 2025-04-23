import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] durability = new int[n * 2];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < durability.length; i++) {
            durability[i] = Integer.parseInt(st.nextToken());
        }

        Conveyor conveyor = new Conveyor(n, durability, k);
        System.out.print(conveyor.convey());
    }

    private static class Conveyor {
        private int up, down; // 올리는, 내리는 위치
        private int size; // 컨베이어벨트 총 길이
        private int fault, limit; // 내구도가 0인 칸의 갯수, 0인 칸의 최대 갯수
        private int[] durability; // 각 칸의 내구도
        private boolean[] fill; // 각 칸의 로봇 존재 유무

        public Conveyor(int n, int[] durability, int limit) {
            this.up = 0;
            this.down = n - 1;
            this.size = n * 2;
            this.limit = limit;
            this.durability = durability;
            this.fill = new boolean[size];
        }

        public int convey() {
            int processCount = 0;
            while (fault < limit) {
                processCount++;

                moveBelt();
                moveRobot();
                putOn();
            }

            return processCount;
        }

        private void moveBelt() {
            up = up == 0 ? size - 1 : up - 1;

            fill[down] = false;
            down = down == 0 ? size - 1 : down - 1;
            fill[down] = false;
        }

        private void moveRobot() {
            int cur = down;
            while (cur != up) {
                int next = cur;
                cur = cur == 0 ? size - 1 : cur - 1;

                if (!fill[cur] || fill[next] || durability[next] == 0) {
                    continue;
                }

                fill[cur] = false;
                fill[next] = true;
                if (--durability[next] == 0) {
                    fault++;
                }
            }
        }

        private void putOn() {
            // 컨베이어 벨트에 로봇을 올림
            if (durability[up] > 0) {
                fill[up] = true;
                if (--durability[up] == 0) {
                    fault++;
                }
            }
        }
    }
}
