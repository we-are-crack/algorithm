import java.util.*;

class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        int lastBoardingTime = 540 + ((n - 1) * t);
        int maxPassengers = t * m;
        
        int[] passengers = new int[timetable.length];
        for (int i = 0; i < timetable.length; i++) {
            passengers[i] = convert2IntMinute(timetable[i]);
        }
        
        Arrays.sort(passengers);
        
        List<Integer>[] boarding = new List[n];
        int pIdx = 0;
        for (int i = 0; i < n; i++) {
            boarding[i] = new ArrayList<>();
            
            int boardingTime = 540 + (i * t);
            while (pIdx < passengers.length && passengers[pIdx] <= boardingTime && boarding[i].size() < m) {
                boarding[i].add(passengers[pIdx++]);
            }
        }
        
        int answer = lastBoardingTime;
        while (m <= findInsertPosition(boarding[n - 1], answer--));
        
        return convert2StringTime(answer + 1);
    }
    
    private int convert2IntMinute(String time) {
        return Integer.parseInt(time.substring(0, 2)) * 60 + Integer.parseInt(time.substring(3, 5));
    }
    
    private String convert2StringTime(int time) {
        int hour = time / 60;
        int minute = time % 60;
        
        return new StringBuilder()
            .append(String.format("%02d", hour))
            .append(":")
            .append(String.format("%02d", minute))
            .toString();
    }
    
    private int findInsertPosition(List<Integer> passengers, int target) {
        int p = 0;
        for (int pas : passengers) {
            if (pas <= target) {
                p++;
            } else break;
        }
        
        return p;
    }
}