import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        Arrays.sort(times);
        return bs(times, n);
    }
    private long bs(int[] times, long people) {
        long start = 1;
        long end = (long) times[times.length - 1] * people;
        long mid;
        long immigrationTime;
        while(start < end) {
            mid = (end + start) / 2;
            immigrationTime = getImmigrationTime(times, people, mid);
            if(mid == immigrationTime) end = mid;
            else if(mid < immigrationTime) start = mid + 1;
            else end = mid - 1;
        }
        return start;
    }
    private long getImmigrationTime(int[] times, long people, long target) {
        long immigrationTime = 0;
        long processingPeople;
        for(int i = times.length - 1; i > 0; i--) {
            if(people <= 0) break;
            processingPeople = target / times[i];
            immigrationTime = Math.max(immigrationTime, processingPeople * times[i]);
            people -= processingPeople;
        }
        return Math.max(immigrationTime, people * times[0]);
    }
}