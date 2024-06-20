class Solution {
    
    public int solution(int[] a) {
        int n = a.length;
        int[] left = new int[n]; // left[i] = 0 ~ i번째까지 원소 중 최솟값
        int[] right = new int[n]; // right[i] = n ~ n-i번째까지 원소 중 최솟값
        
        left[0] = a[0];
        right[n - 1] = a[n - 1];
        for (int i = 1; i < n; i++) {
            left[i] = Math.min(left[i - 1], a[i]);
            right[n - 1 - i] = Math.min(right[n - i], a[n - 1 - i]);
        }
        
        if (n <= 2) {
            return n;
        }
        
        int answer = 2;
        for (int i = 1; i < n - 1; i++) {
            int l = left[i - 1];
            int m = a[i];
            int r = right[i + 1];
            
            if (m < l || m < r) {
                answer++;
            }
        }
        
        return answer;
    }
}