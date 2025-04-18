import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] height = new int[1001];

        int n = Integer.parseInt(br.readLine());
        int left = 1001, right = 0, maxHeight = 0;
        while (n-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int pos = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            height[pos] = h;

            left = Math.min(left, pos);
            right = Math.max(right, pos);
            maxHeight = Math.max(maxHeight, h);
        }

        int[] leftArea = getArea(height, maxHeight, left, 1);
        int[] rightArea = getArea(height, maxHeight, right, -1);
        
        int answer = maxHeight * (rightArea[0] - leftArea[0] + 1);
        answer += leftArea[1] + rightArea[1];

        System.out.print(answer);
    }

    /**
     * pos 좌표부터 direction(1 or -1) 방향으로 높이가 maxHeight보다 낮은 좌표까지의 영역에 대해
     * [영역의 끝 좌표, 영역의 넓이] 리턴
     */
    public static int[] getArea(int[] height, int maxHeight, int pos, int direction) {
        int area = 0, mh = height[pos];
        while (height[pos] < maxHeight) {
            area += (mh = Math.max(mh, height[pos]));
            pos += direction;
        }

        return new int[]{pos, area};
    }
}
