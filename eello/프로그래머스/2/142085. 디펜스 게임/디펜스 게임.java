import java.util.*;

class Solution {
    
    /*
        최대 막을 수 있는 라운드까지 중 가장 많은 적의 수가 있는 라운드에
        무적권을 사용하는게 최선
        
        1. 일단 보유한 병사로 라운드 클리어, 라운드 클리어를 위해 사용한 병사의 수 pq에 추가 (pq는 내림차순 정렬)
        2. i번째 라운드에 enemy[i] 보다 보유한 병사 수가 적은 경우 -> 무적권을 사용해야하는 경우
            if enemy[i] < pq.peek() then
                보유한 병사수 += (pq.poll() - enemy[i]);
                pq.add(enemy[i]);    
            무적권 -= 1;
        3. 1~2 과정을 반복하다 현재 보유한 병사 수 < enemy[i] && 무적권 == 0 이면 게임 종료
    */
    
    public int solution(int n, int k, int[] enemy) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        
        int round = 0;
        for (; round < enemy.length; round++) {
            if (enemy[round] <= n) {
                n -= enemy[round];
                pq.add(enemy[round]);
            } else {
                if (k == 0) {
                    break;   
                }
                
                if (!pq.isEmpty() && enemy[round] < pq.peek()) {
                    n += (pq.poll() - enemy[round]);
                    pq.add(enemy[round]);
                }
                
                k--;
            }
        }
        
        return round;
    }
}