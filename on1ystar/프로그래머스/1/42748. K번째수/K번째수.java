import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        ArrayList<Integer> li = new ArrayList<>();
        for(int i = 0; i < commands.length; i++) {
            for(int j = commands[i][0] - 1; j < commands[i][1]; j++) {
                li.add(array[j]);
            }
            li.sort(Integer::compareTo);
            answer[i] = li.get(commands[i][2] - 1);
            li.clear();
        }
        return answer;
    }
}