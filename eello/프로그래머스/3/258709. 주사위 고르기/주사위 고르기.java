import java.util.*;

class Solution {
    
    private int n;
    private Dice answer;
    
    public int[] solution(int[][] dice) {
        n = dice.length;
        combination(dice, new int[n / 2], 0, 0, new BitSet(), 0);
        return answer.ids;
    }
    
    private void combination(int[][] dice, int[] sel, int idx, int k, BitSet visit, int bitmask) {
        if (sel.length == k) {
            if (visit.get(bitmask)) {
                return;
            }

            int[] other = new int[sel.length];
            int otherBitmask = 0, oi = 0;
            for (int d = 1; d <= dice.length; d++) {
                if (isSelected(sel, d)) {
                    continue;
                }
                
                otherBitmask |= 1 << d;
                other[oi++] = d;
            }
            
            visit.set(bitmask);
            visit.set(otherBitmask);
            
            Dice d1 = getDice(dice, sel);
            Dice d2 = getDice(dice, other);
            Dice win = d1.vs(d2);
            
            if (answer == null || answer.win < win.win) {
                answer = win;
            }
            
            return;
        }
        
        if (dice.length == idx) {
            return;
        }
        
        combination(dice, sel, idx + 1, k, visit, bitmask);
        sel[k] = idx + 1;
        combination(dice, sel, idx + 1, k + 1, visit, bitmask | 1 << (idx + 1));
    }
    
    private Dice getDice(int[][] dice, int[] sel) {
        int[][] selDice = new int[sel.length][];
        for (int i = 0; i < sel.length; i++) {
            selDice[i] = dice[sel[i] - 1];
        }
        
        Dice d = new Dice(sel);
        d.addDice(selDice);
        
        return d;
    }
    
    private boolean isSelected(int[] sel, int num) {
        for (int s : sel) {
            if (s == num) {
                return true;
            }
        }
        
        return false;
    }
    
    private static class Dice {
        private int[] ids;
        private Map<Integer, Integer> allCase = new HashMap<>(); // key = 조합된 수, value = 개수
        private int[] count; // 누적합
        private int win;
        
        public Dice(int... ids) {
            this.ids = Arrays.copyOf(ids, ids.length);
            count = new int[ids.length * 100 + 1];
            Arrays.sort(ids);
        }
        
        public void addDice(int[][] dice) {
            Queue<Integer> que = new ArrayDeque<>();
            que.add(0);
            
            for (int i = 0; i < dice.length; i++) {
                int queSize = que.size();
                
                for (int j = 0; j < queSize; j++) {
                    int number = que.poll();
                    
                    for (int v : dice[i]) {
                        que.add(number + v);
                    }
                }
            }
            
            while (!que.isEmpty()) {
                int num = que.poll();
                count[num]++;
                allCase.put(num, allCase.getOrDefault(num, 0) + 1);
            }
            
            for (int i = 1; i < count.length; i++) {
                count[i] += count[i - 1];
            }
        }
        
        public Dice vs(Dice other) {
            int w = 0, l = 0;
            for (Map.Entry<Integer, Integer> entry : allCase.entrySet()) {
                int key = entry.getKey();
                int val = entry.getValue();
                
                w += other.count[key - 1] * val;
                l += (other.count[other.count.length - 1] - other.count[key]) * val;
            }
            
            setWin(w);
            other.setWin(l);
            
            return win > other.win ? this : other;
        }
        
        private void setWin(int win) {
            this.win = win;
        }
    }
}