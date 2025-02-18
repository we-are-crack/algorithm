import java.util.*;

class Solution {
    public int solution(int[] players, int m, int k) {
        int answer = 0;

        // {사각, 증설된 서버 수}
        Queue<int[]> q = new LinkedList<>();
        int currentServerCnt = 0;

        for (int h = 0; h < 24; h++) {
            while (!q.isEmpty()) {
                if (h - q.peek()[0] == k) {
                    currentServerCnt -= q.peek()[1];
                    q.remove();
                } else {
                    break;
                }
            }

            int needServerCnt = players[h] / m;
            if (needServerCnt > currentServerCnt) {
                q.add(new int[]{h, needServerCnt - currentServerCnt});
                answer += needServerCnt - currentServerCnt;
                currentServerCnt = needServerCnt;
            }

        }

        return answer;
    }
}