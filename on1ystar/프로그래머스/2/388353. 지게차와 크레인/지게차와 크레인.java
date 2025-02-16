import java.util.*;

class Solution {

    private static final int[] dr = {1, 0, -1, 0};
    private static final int[] dc = {0, 1, 0, -1};

    public int solution(String[] storage, String[] requests) {
        int n = storage.length;
        int m = storage[0].length();
        int answer = n * m;
        boolean[][] released = new boolean[n][m];
        boolean[][] connected = new boolean[n][m];

        List<List<int[]>> containers = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            containers.add(new LinkedList<>());
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                containers.get(storage[i].charAt(j) - 'A').add(new int[]{i, j});
            }
        }

        for (String s : requests) {
            List<int[]> containerList = containers.get(s.charAt(0) - 'A');
            // 크레인 출고
            if (s.length() == 2) {
                for (int[] pos : containerList) {
                    if (!released[pos[0]][pos[1]]) {
                        released[pos[0]][pos[1]] = true;
                        answer--;
                    }
                }
            } else {
                // 지게차 출고
                List<int[]> releasedContainerList = new ArrayList<>();

                for (int[] pos : containerList) {
                    if (!released[pos[0]][pos[1]] && checkConnectable(pos[0], pos[1], n, m, connected, released, new boolean[n][m])) {
                        releasedContainerList.add(pos);
                    }
                }
                
                for (int[] pos : releasedContainerList) {
                    released[pos[0]][pos[1]] = true;
                    answer--;
                }
            }
        }

        return answer;
    }

    private boolean checkConnectable(int r, int c, int n, int m, boolean[][] connected, boolean[][] released, boolean[][] visited) {
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            // 가장자리거나, 외부와 연결된 컨테이너
            if (0 > nr || nr >= n || 0 > nc || nc >= m || connected[nr][nc]) {
                return true;
            }

            if (released[nr][nc] && !visited[nr][nc]) {
                visited[nr][nc] = true;

                if (checkConnectable(nr, nc, n, m, connected, released, visited)) {
                    connected[nr][nc] = true;
                    return true;
                }
            }
        }

        return false;
    }
}