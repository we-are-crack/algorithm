import java.util.*;

class Solution {
    public int solution(int n, int[] cores) {
        int minTime = 1;
        int maxTime = (n / cores.length + 1) * 10_000;

        while (maxTime > minTime) {
            int midTime = (maxTime + minTime) / 2;
            int processed = process(cores, midTime, n);

            if (processed >= n) {
                maxTime = midTime;
            } else {
                minTime = midTime + 1;
            }
        }

        List<Integer> coreList = new ArrayList<>();
        for (int i = 0; i < cores.length; i++) {
            if (maxTime % cores[i] == 0) {
                coreList.add(i);
            }
        }

        return coreList.get(coreList.size() - 1 - (process(cores, maxTime, n) - n)) + 1;
    }

    private int process(int[] cores, int time, int n) {
        if (cores.length >= n) {
            return n;
        }

        int processed = cores.length;
        for (int core : cores) {
            processed += time / core;
        }

        return processed;
    }
}