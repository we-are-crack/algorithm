import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
    public int solution(int[][] points, int[][] routes) {
        int crash = 0;
        int[][] coords = new int[101][101];
        List<Robot> robots = new ArrayList<>();
        Set<Integer> nowPosOfRobots = new HashSet<>();

        for (int i = 0; i < routes.length; i++) {
            int[] startPoint = points[routes[i][0] - 1];
            robots.add(new Robot(startPoint[0], startPoint[1], routes[i]));
        }

        while (true) {
            for (Robot robot : robots) {
                if (!robot.isComplete()) {
                    int[] nowPos = robot.go(points[robot.getNextRoute() - 1]);
                    nowPosOfRobots.add(nowPos[0] * 101 + nowPos[1]);
                    coords[nowPos[0]][nowPos[1]]++;
                }
            }

            if (nowPosOfRobots.isEmpty()) {
                break;
            }

            for (int pos : nowPosOfRobots) {
                int r = pos / 101;
                int c = pos % 101;

                if (coords[r][c] > 1) {
                    crash++;
                }

                coords[r][c] = 0;
            }

            nowPosOfRobots.clear();

        }

        return crash;
    }

    class Robot {

        private int r;
        private int c;
        private int[] route;
        private int nowRouteIndex = -1;

        public Robot(int r, int c, int[] route) {
            this.r = r;
            this.c = c;
            this.route = route;
        }

        public int getNextRoute() {
            return route[nowRouteIndex + 1];
        }

        public int[] go(int[] nextPoint) {
            if (nextPoint[0] > r) {
                r++;
            } else if (nextPoint[0] < r) {
                r--;
            } else {
                if (nextPoint[1] > c) {
                    c++;
                } else if (nextPoint[1] < c) {
                    c--;
                }
            }

            if (nextPoint[0] == r && nextPoint[1] == c) {
                nowRouteIndex++;
            }

            return new int[] {r, c};
        }

        public boolean isComplete() {
            return nowRouteIndex == route.length - 1;
        }
    }
}