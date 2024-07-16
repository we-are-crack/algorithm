import java.util.*;

class Solution {
    public int solution(int distance, int[] rocks, int n) {
        Arrays.sort(rocks);
        int[] betweenDistance = new int[rocks.length + 1];
        int pre = 0;
        for(int i = 0; i < rocks.length; i++) {
            betweenDistance[i] = rocks[i] - pre;
            pre = rocks[i];
        }
        betweenDistance[betweenDistance.length - 1] = distance - pre;

        return bs(betweenDistance, n, distance);
    }
    private int bs(int[] betweenDistance, int deletingRocks, int distance) {
        int start = 0;
        int end = distance;
        int minDistance;  // minimum distance
        while(start <= end) {
            minDistance = (start + end) / 2;
            int rocks = deleteRocks(betweenDistance, minDistance);
            if(rocks <= deletingRocks) start = minDistance + 1;
            else end = minDistance - 1;
        }
        return end;
    }
    private int deleteRocks(int[] betweenDistance, int minDistance) {
        int rocks = 0;
        int preDistance = 0;
        for (int i = 0; i < betweenDistance.length; i++) {
            if (betweenDistance[i] + preDistance < minDistance) {
                rocks++;
                preDistance += betweenDistance[i];
                continue;
            }
            preDistance = 0;
        }
        return rocks;
    }
}