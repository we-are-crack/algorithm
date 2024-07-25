import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] triangle = new int[n][1];
        triangle[0][0] = sc.nextInt();
        for(int i = 1; i < n; i++) {
            triangle[i] = new int[i + 1];
            for(int j = 0; j < i + 1; j++) {
                triangle[i][j] = sc.nextInt();
            }
        }
        int answer = 0;
        int left, right;
        int[][] d = new int[triangle.length][triangle[0].length];
        d[0][0] = triangle[0][0];
        for(int i = 1; i < triangle.length; i++) {
            d[i] = new int[triangle[i].length];
            for(int j = 0; j < triangle[i].length; j++) {
                left = 0;
                right = 0;
                if(j - 1 >= 0) left = d[i - 1][j - 1];
                if(j < triangle[i - 1].length) right = d[i - 1][j];
                d[i][j] = triangle[i][j] + Math.max(left, right);
            }
        }
        for(int num : d[d.length - 1]) {
            answer = Math.max(answer, num);
        }
        System.out.println(answer);;
    }
}