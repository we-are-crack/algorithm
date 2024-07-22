import java.util.*;

class Solution {
    public int solution(int[] elements) {
        int len = elements.length;
        
        int[] acc = new int[2 * len - 1];
        acc[0] = elements[0];
        for (int i = 1; i < acc.length; i++) {
            acc[i] = acc[i - 1] + elements[i % len];
        }
        
        Set<Integer> set = new HashSet<>();
        for (int i = 1; i <= len; i++) {
            for (int j = i - 1; j < acc.length; j++) {
                set.add(j - i < 0 ? acc[j] : acc[j] - acc[j - i]);
            }
        }
        
        return set.size();
    }
}