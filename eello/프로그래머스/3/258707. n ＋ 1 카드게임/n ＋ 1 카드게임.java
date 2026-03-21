import java.util.*;

class Solution {
    public int solution(int coin, int[] cards) {
        int answer = 0;
        int n = cards.length;
        int round = 0, index = 0;
        
        // hand[i] = 숫자가 i인 카드에 대해 값이 
        // -1: 경우 없거나 사용한 카드
        // 0: 경우 처음부터 가지고 있던 카드
        // 1: 경우 코인을 사용해야 얻을 수 있는 카드
        int[] hand = new int[n + 1];
        Arrays.fill(hand, -1);
        for (; index < n / 3; index++) {
            int card = cards[index];
            hand[card] = 0;
        }
        
        int target = n + 1;
        while (true) {
            round++;
            
            if (index >= cards.length) {
                break;
            }
            
            hand[cards[index++]] = 1;
            hand[cards[index++]] = 1;
            
            boolean flag = false;
            PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
            for (int num = 1; num <= (n / 2); num++) {
                int other = target - num;
                if (
                    hand[num] >= 0 && hand[other] >= 0
                    && coin - (hand[num] + hand[other]) >= 0
                ) {
                    pq.add(new int[]{num, other, hand[num] + hand[other]});
                }
            }
            
            if (pq.isEmpty()) break;
            
            int[] submit = pq.poll();
            int card1 = submit[0];
            int card2 = submit[1];
            int needCoin = submit[2];
            
            hand[card1] = -1;
            hand[card2] = -1;
            coin -= needCoin;
        }
        
        return round;
    }
}