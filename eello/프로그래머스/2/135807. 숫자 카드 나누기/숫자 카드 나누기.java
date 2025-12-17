import java.util.*;

class Solution {
    
    /*
        각 배열의 공약수 집합의 원소들 중 교집합이 되지 않는 원소 중 최댓값을 리턴
        
        → n개의 원소가 있는 배열에 대한 공약수 집합을 구해야 함
            1. 각 배열의 최소값 원소에 대한 약수들 구함: a
            2. a를 사용해서 해당 배열에 대한 최대 공약수를 구함: gcd
            3. gcd의 약수 집합이 해당 배열에 대한 공약수 집합
            
        각 배열에 대한 공약수 집합의 원소 중 겹치지 않는 원소의 최대값부터 반대편 원소를 나눌 수 있는지 없는지 검사
    */
    
    public int solution(int[] arrayA, int[] arrayB) {
        int gcdA = getGcd(arrayA);
        int gcdB = getGcd(arrayB);
        
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        pq.addAll(getDivisors(gcdA));
        pq.addAll(getDivisors(gcdB));
        
        int answer = 0;
        while (!pq.isEmpty()) {
            int divisor = pq.poll();
            
            int[] targetArray; // divisor로 나눌 수 있는 숫자가 있는지 검사해야할 배열
            if (gcdA % divisor == 0 && gcdB % divisor == 0) {
                // 두 배열 모두에 대한 약수인 경우(겹치는 약수인 경우)
                continue;
            } else if (gcdA % divisor == 0) {
                // arrayA에 대한 약수이므로 arrayB에 대해 나눌 수 있는 숫자가 있는지 검사
                targetArray = arrayB;
            } else {
                // arrayB에 대한 약수이므로 arrayA에 대해 나눌 수 있는 숫자가 있는지 검사
                targetArray = arrayA;
            }
            
            boolean flag = true;
            for (int elem : targetArray) {
                if (elem % divisor == 0) {
                    flag = false;
                    break;
                }
            }
            
            if (flag) {
                answer = divisor;
                break;
            }
        }
        
        return answer;
    }
    
    private int getGcd(int[] array) {
        // 배열의 최소 원소의 약수를 구함
        Arrays.sort(array);
        int min = array[0];
        
        List<Integer> divisors = getDivisors(min);
        
        // 배열의 최소 원소의 약수로 해당 배열의 최대 공약수를 구함
        int gcd = 1;
        for (Integer div : divisors) {           
            boolean flag = true;
            for (int elem : array) {
                if (elem % div != 0) {
                    flag = false;
                    break;
                }
            }
            
            if (flag) {
                gcd = div;
                break;
            }
        }
        
        return gcd;
    }
    
    // 약수 배열을 오름차순으로 리턴
    private List<Integer> getDivisors(int number) {
        List<Integer> divisors = new ArrayList<>();
        
        int sqrt = (int) Math.sqrt(number);
        for (int div = 1; div <= sqrt; div++) {
            if (number % div != 0) {
                continue;
            }
            
            divisors.add(div);
            if (number / div != div) {
                divisors.add(number / div);
            }
        }
        
        divisors.sort(Comparator.reverseOrder());
        return divisors;
    }
}