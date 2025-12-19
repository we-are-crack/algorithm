import java.util.*;

class Solution {
    
    /*
        1 ~ n이 포함된 list{1, 2, 3, 4, ..., n}이 있을 때
        i번째 위치할 숫자는 리스트의 k / (i - 1)!번째 인덱스에 위치한 숫자
        = answer[i] = list.get(k / factorial[i - 1])
        
        사용된 숫자는 리스트에서 제거: list.remove(k / (i - 1)!)
        
        * k는 1부터 시작하므로 k -= 1을 해주어 0부터 시작하도록 변경
        이렇게 하지 않는다면 리스트에서 각 자리수에 해당하는 숫자의 인덱스를 구할 때 나누어 떨어지는 경우에 대해 복잡해짐
        
        n = 3 일때, k / (i - 1)!의 값
        
        첫번째 자리 순서:  1 1 2 2 3 3
        k:             1 2 3 4 5 6
        k / 2!:        0 1 1 2 2 3

        첫번째 자리 순서:  1 1 2 2 3 3
        k -= 1:        0 1 2 3 4 5
        k / 2!:        0 0 1 1 2 2
        
        즉, k -= 1을 적용했을 때 k / (i - 1)!의 값에 따라 매칭되는 숫자를 결정 가능
        
        따라서 k -= 1 이후 answer[i] = list.get(k / factorial[i - 1])를 i: 0 ~ n-1까지 반복하면 오름차순으로 줄을 섰을 때 k번째 방법을 구할 수 있음
    */
    
    public int[] solution(int n, long k) {
        long[] factorial = new long[n + 1];
        factorial[0] = 1;
        List<Integer> number = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            factorial[i] = i * factorial[i - 1];
            number.add(i);
        }
        
        k -= 1;
        
        int[] answer = new int[n];
        for (int i = 0; i < n; i++) {
            int mok = (int) (k / factorial[n - i - 1]);
            
            answer[i] = number.get(mok);
            number.remove(mok);
            
            k %= factorial[n - i - 1];
        }
        
        return answer;
    }
}