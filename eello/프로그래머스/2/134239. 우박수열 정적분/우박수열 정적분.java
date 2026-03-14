import java.util.*;

class Solution {
    public double[] solution(int k, int[][] ranges) {
        List<Integer> hailstone = getHailstoneSeq(k);
        int n = hailstone.size() - 1;
        List<Double> area = calculateArea(hailstone);
        
        double[] answer = new double[ranges.length];
        for (int i = 0; i < ranges.length; i++) {
            int left = ranges[i][0];
            int right = n + ranges[i][1];
            
            if (right < left) {
                answer[i] = -1;
                continue;
            }
            
            answer[i] = area.get(right) - area.get(left);
        }
        
        return answer;
    }
    
    private List<Integer> getHailstoneSeq(int n) {
        List<Integer> seq = new ArrayList<>();
        seq.add(n);
        
        while (n > 1) {
            if (n % 2 == 0) {
                n /= 2;
            } else n = (n * 3) + 1;
            
            seq.add(n);
        }
        
        return seq;
    }
    
    private List<Double> calculateArea(List<Integer> seq) {
        List<Double> area = new ArrayList<>();
        area.add(0.0);
        
        double acc = 0.0;
        for (int i = 1; i < seq.size(); i++) {
            int diff = Math.abs(seq.get(i - 1) - seq.get(i));
            int minH = Math.min(seq.get(i - 1), seq.get(i));
            
            acc += minH + (diff / 2.0);
            area.add(acc);
        }
        
        return area;
    }
}