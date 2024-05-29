class Solution {
    public int solution(int n, int[] stations, int w) {
        /**
         * 전파가 닿지 않는 연속된 구간에 필요한 최소 기지국 수를 계산
         */
        int answer = 0, start = 1, coverArea = 2 * w + 1;
        for (int station : stations) {
            if (start < station - w) {
                int signalNeedHouseCount = (station - w - start);
                answer += signalNeedHouseCount / coverArea;
                answer += signalNeedHouseCount % coverArea > 0 ? 1 : 0;
            }
            
            start = station + w + 1;
        }
        
        if (start <= n) {
            int signalNeedHouseCount = (n - start + 1);
            answer += signalNeedHouseCount / coverArea;
            answer += signalNeedHouseCount % coverArea > 0 ? 1 : 0;
        }
        
        return answer;
    }
}