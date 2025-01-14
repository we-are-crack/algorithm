import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] request = new int[n];
        int maxRequest = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            request[i] = Integer.parseInt(st.nextToken());
            maxRequest = Math.max(maxRequest, request[i]);
        }
        int budget = Integer.parseInt(br.readLine());

        if (isValid(request, budget, maxRequest)) {
            System.out.println(maxRequest);
            return;
        }

        int left = 1, right = budget;
        int answer = 0;
        while (left <= right) {
            int b = (left + right) / 2;

            if (isValid(request, budget, b)) {
                answer = b;
                left = b + 1;
            } else right = b - 1;
        }

        System.out.println(answer);
    }

    /**
     * 각 지방당 최대 b만큼 예산을 지급했을 때 budget이 모자라다면 false 리턴, 충분하다면 true
     * @param request 각 지방에서 요창한 예산 금액
     * @param budget 국가 총 예산
     * @param b 각 지방에 최대로 지급할 수 있는 예산 금액
     * @return 각 지방당 최대 b만큼 예산을 지급했을 때 budget이 모자라다면 false 리턴, 충분하다면 true
     */
    private static boolean isValid(int[] request, int budget, int b) {
        int temp = 0;
        for (int r : request) {
            temp += Math.min(r, b);
        }

        return temp <= budget;
    }
}
