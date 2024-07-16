import java.util.*;

class Solution {
    public String solution(String s) {
        String[] split = s.split(" ");
        
        int[] num = new int[split.length];
        for (int i = 0; i < num.length; i++) {
            num[i] = Integer.parseInt(split[i]);
        }
        
        Arrays.sort(num);
        
        return String.format("%d %d", num[0], num[num.length - 1]);
    }
}