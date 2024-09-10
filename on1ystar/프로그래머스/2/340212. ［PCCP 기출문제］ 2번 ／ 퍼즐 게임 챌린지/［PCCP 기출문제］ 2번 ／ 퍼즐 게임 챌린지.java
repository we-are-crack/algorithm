class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int minLevel = 1;
        int maxLevel = 100_000;

        while (minLevel < maxLevel) {
            int curLevel = (maxLevel + minLevel) / 2;
            long solvingTime = solvePuzzle(diffs, times, curLevel);
            if (solvingTime <= limit) {
                maxLevel = curLevel;
            } else {
                minLevel = curLevel + 1;
            }
        }

        return maxLevel;
    }

    private long solvePuzzle(int[] diffs, int[] times, int level) {
        long timeAll = diffs[0] - level > 0 ? (diffs[0] - level) * times[0] : times[0];

        for (int i = 1; i < diffs.length; i++) {
            if (diffs[i] > level) {
                timeAll += (diffs[i] - level) * (times[i - 1] + times[i]) + times[i];
            } else {
                timeAll += times[i];
            }
        }

        return timeAll;
    }
}