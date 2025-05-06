import java.util.*;

class Solution {
    
    public int solution(int[] a) {
        Map<Integer, Integer> elemCount = new HashMap<>();
        for (int num : a) {
            elemCount.put(num, elemCount.getOrDefault(num, 0) + 1);
        }
        
        if (elemCount.size() == 1) {
            return 0;
        }
        
        List<Map.Entry<Integer, Integer>> entry = new ArrayList<>(elemCount.entrySet());
        entry.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
        
        int answer = 2;
        for (Map.Entry<Integer, Integer> e : entry) {
            int target = e.getKey();
            int count = e.getValue();
            
            if (answer / 2 >= count) {
                break;
            }
            
            answer = Math.max(answer, getStarSequenceLength(a, target));
        }
        
        
        return answer;
    }
    
    private int getStarSequenceLength(int[] arr, int target) {
        boolean t = false, n = false;
        int length = 0;
        for (int a : arr) {
            t |= a == target;
            n |= a != target;
            
            if (t & n) {
                t = false;
                n = false;
                length += 2;
            }
        }
        
        return length;
    }
}