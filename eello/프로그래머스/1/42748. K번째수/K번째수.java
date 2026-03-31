import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        for (int i = 0; i < answer.length; i++) {
            int[] command = commands[i];
            int s = command[0] - 1;
            int e = command[1];
            int t = command[2] - 1;
            
            List<Integer> subarray = new ArrayList<>();
            for (int j = s; j < e; j++) {
                subarray.add(array[j]);
            }
            subarray.sort(Comparator.comparingInt(a -> a));
            
            answer[i] = subarray.get(t);
        }
        
        return answer;
    }
}