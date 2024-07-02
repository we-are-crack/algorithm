import java.util.*;

class Solution {
    
    private TreeSet<Integer> candidate = new TreeSet<>();
    
    public int solution(String numbers) {
        combination(numbers.toCharArray(), new boolean[numbers.length()], new StringBuilder(), 0);
        while (candidate.first() < 2) {
            candidate.pollFirst();
        }

        boolean[] notPrime = new boolean[candidate.last() + 1];
        int sqrt = (int) Math.sqrt((double) candidate.last());
        for (int i = 2; i <= sqrt; i++) {
            if (notPrime[i]) {
                continue;
            }
            
            int j = 2;
            while (i * j <= candidate.last()) {
                notPrime[i * j++] = true;
            }
        }
        
        int answer = 0;
        for (int num : candidate) {
            if (!notPrime[num]) {
                answer++;
            }
        }
        
        return answer;
    }
    
    private void combination(char[] numbers, boolean[] used, StringBuilder sb, int idx) {
        for (int i = 0; i < numbers.length; i++) {
            if (!used[i]) {
                used[i] = true;
                
                sb.append(numbers[i]);
                candidate.add(Integer.parseInt(sb.toString()));
                combination(numbers, used, sb, idx + 1);
                
                used[i] = false;
                sb.deleteCharAt(idx);
            }
        }
    }
}