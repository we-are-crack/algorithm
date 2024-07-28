import java.util.*;

class Solution {
    
    public int[] solution(String s) {
        Map<Integer, Integer> elem = new HashMap<>();
        
        String[] arr = s.substring(2, s.length() - 2).split("},\\{");
        
        for (String str : arr) {
            StringTokenizer st = new StringTokenizer(str, ",");
            while (st.hasMoreTokens()) {
                int num = Integer.parseInt(st.nextToken());
                elem.put(num, elem.getOrDefault(num, 0) + 1);
            }
        }
        
        List<Map.Entry<Integer, Integer>> entry = new ArrayList<>(elem.entrySet());
        Collections.sort(entry, Map.Entry.comparingByValue(Comparator.reverseOrder()));
        
        int[] answer = new int[entry.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = entry.get(i).getKey();
        }
        
        return answer;
    }
}