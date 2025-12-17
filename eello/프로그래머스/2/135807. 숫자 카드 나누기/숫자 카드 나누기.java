import java.util.*;

class Solution {
    
    /*
        각 배열의 공약수 집합의 원소들 중 교집합이 되지 않는 원소 중 최댓값을 리턴
        
        → n개의 원소가 있는 배열에 대한 공약수 집합을 구해야 함
            1. 각 배열의 최소값 원소에 대한 약수들 구함: a
            2. a를 사용해서 해당 배열에 대한 최대 공약수를 구함: gcd
            3. gcd의 약수 집합이 해당 배열에 대한 전체 공약수 집합
            
        각 배열에 대한 공약수 집합의 원소 중 겹치지 않는 원소의 최대값부터 반대편 원소를 나눌 수 있는지 없는지 검사
    */
    
    public int solution(int[] arrayA, int[] arrayB) {
        int[][] array = new int[2][];
        array[0] = arrayA;
        array[1] = arrayB;
        
        List<Integer> cdA = getCommonDivisors(arrayA);
        List<Integer> cdB = getCommonDivisors(arrayB);
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        for (Integer cda : cdA) {
            pq.add(new int[] {cda, 1});
        }
        
        for (Integer cdb : cdB) {
            pq.add(new int[] {cdb, 0});
        }
        
        int answer = 0;
        while (!pq.isEmpty()) {
            int[] divisor = pq.poll();
            
            int div = divisor[0];
            int[] targetArray = array[divisor[1]];
            
            boolean flag = true;
            for (int elem : targetArray) {
                if (elem % div == 0) {
                    flag = false;
                    break;
                }
            }
            
            if (flag) {
                answer = div;
                break;
            }
        }
        
        return answer;
    }
    
    private boolean isCommonDivisor(int[] array, int divisor) {
        for (int elem : array) {
            if (elem % divisor != 0) {
                return false;
            }
        }
        return true;
    }
    
    private List<Integer> getCommonDivisors(int[] array) {
        // 배열의 최소 원소의 약수를 구함
        Arrays.sort(array);
        int min = array[0];
        
        List<Integer> divisors = getDivisors(min);
        
        // 배열의 최소 원소의 약수로 해당 배열의 최대 공약수를 구함
        int gcd = 1;
        for (Integer div : divisors) {
            gcd = div;
            
            boolean flag = true;
            for (int elem : array) {
                if (elem % gcd != 0) {
                    flag = false;
                    break;
                }
            }
            
            if (flag) {
                break;
            }
        }
        
        // 최대 공약수의 약수가 배열의 공약수 집합
        List<Integer> commonDivisors = getDivisors(gcd);
        return commonDivisors;
    }
    
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