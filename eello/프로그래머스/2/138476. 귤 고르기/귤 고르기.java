import java.util.*;

class Solution {
    
    /**
     *  Map을 사용한 풀이가 단순 리스트를 사용한 풀이보다 더 효율적인듯
     */
    
    /**
     *  단순 리스트를 사용한 풀이
     */
    public int solution(int k, int[] tangerine) {
        Arrays.sort(tangerine);
        
        List<Integer> count = new ArrayList<>();
        
        int prev = tangerine[0], cnt = 1;
        for (int i = 1; i < tangerine.length; i++) {
            if (prev != tangerine[i]) {
                count.add(cnt);
                cnt = 1;
                prev = tangerine[i];
            } else cnt++;
        }
        count.add(cnt);
        
        Collections.sort(count, Comparator.reverseOrder());
        
        int answer = 0;
        for (int c : count) {
            answer++;
            k -= c;
            
            if (k <= 0) {
                break;
            }
        }
        
        return answer;
    }
    
    
    /**
     *  Map을 사용한 풀이
     */
//     public int solution(int k, int[] tangerine) {
//         Map<Integer, Integer> count = new HashMap<>();
//         for (int size : tangerine) {
//             count.put(size, count.getOrDefault(size, 0) + 1);
//         }
        
//         List<Map.Entry<Integer, Integer>> entrySet = new ArrayList<>(count.entrySet());
//         Collections.sort(entrySet, Map.Entry.comparingByValue(Comparator.reverseOrder()));
        
//         int answer = 0;
//         for (Map.Entry<Integer, Integer> entry : entrySet) {
//             answer++;
//             k -= entry.getValue();
            
//             if (k <= 0) {
//                 break;
//             }
//         }
        
//         return answer;
//     }
}