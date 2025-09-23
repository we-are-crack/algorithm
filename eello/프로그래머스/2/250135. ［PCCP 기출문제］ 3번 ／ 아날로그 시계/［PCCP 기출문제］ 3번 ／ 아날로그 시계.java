class Solution {

    // 초당 각 침의 이동 각도
    // 시침(HAPS) : 1 / 120
    // 분침(MAPS) : 1 / 10
    // 초침(SAPS) : 6
    private static final double HAPS = 1 / 120.0, MAPS = 0.1, SAPS = 6.0;

    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        int answer = 0;
        int start = toSeconds(h1, m1, s1);
        int end = toSeconds(h2, m2, s2);

        // 시침, 분침, 초침이 모두 겹치는 경우는 0:0:0 or 12:0:0 인 경우만 존재
        // if (start == 0) { // 0:0:0 에서 시작하는 경우
        //     answer--;
        // }

        // if (start <= 43200 && 43200 <= end) { // 12:0:0 을 지나는 경우
        //     answer--;
        // }

        for (int t = start; t < end; t++) {
            double cha = getHourAngle(t); // 현재 시침 각도
            double nha = getHourAngle(t + 1); // 1초 후 시침 각도
            if (nha == 0) nha = 360.0;

            double cma = getMinAngle(t); // 현재 분침 각도
            double nma = getMinAngle(t + 1); // 1초 후 분침 각도
            if (nma == 0) nma = 360.0;

            double csa = getSecAngle(t); // 현재 초침 각도
            double nsa = getSecAngle(t + 1); // 1초 후 초침 각도
            if (nsa == 0) nsa = 360.0;

            if (t == start && (csa == cha || csa == cma)) {
                // 시작 시 초침과 시/분침 사이의 일치 검사
                // 일치하면 +1 후 시작
                answer++;
            }

            if (csa < cha && nha <= nsa) { // 1초 움직이는 동안 초침과 시침이 겹침
                answer++;
            }

            if (csa < cma && nma <= nsa) { // 1초 움직이는 동안 초침과 분침이 겹침
                answer++;
            }

            // 시, 분, 초침이 모두 만나는 경우는 모든 침이 12를 가리킬 때가 유일
            // 다음 침의 각도를 나타내는 n?a 는 12를 가리킬 때 360도 이므로
            // 모든 n?a 의 각도가 360 일 때 모든 초침이 만나는 경우이므로 -1
            if (360 == nsa && nsa == nma && nma == nha) {
                answer--;
            }
        }



        return answer;
    }

    private int toSeconds(int h, int m, int s) {
        return 3600 * h + 60 * m + s;
    }

    private double getHourAngle(int sec) {
        return sec * HAPS % 360;
    }

    private double getMinAngle(int sec) {
        return sec * MAPS % 360;
    }

    private double getSecAngle(int sec) {
        return sec * SAPS % 360;
    }
}