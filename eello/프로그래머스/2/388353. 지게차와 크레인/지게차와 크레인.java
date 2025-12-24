import java.util.*;

class Solution {
    
    /*
        storage를 감싸는 패딩을 추가 - 패딩은 모두 빈 공간, 패딩 사이즈 = 2
        ex) n = 2, m = 2일 때 만들어지는 storage
        0 0 0 0
        0 A B 0
        0 C D 0
        0 0 0 0
        
        패딩 공간은 빈 공간으로 창고 외부
        → (0, 0)부터 시작해 빈 공간으로 이동해 접근할 수 있는 경우는 창고 외부와 연결된 컨테이너
            = 지게차로 출고 가능한 컨테이너
        
        지게차로 출고
            - (0, 0)은 추가된 패딩으로 무조건 빈 공간(창고 외부)
            - (0, 0)부터 시작해 BFS를 돌며 접근할 수 있는 요청된 종류의 모든 컨테이너 출고
            - 출고된 컨테이너 수 리턴
            - 출고된 위치는 빈 공간으로 전환
            - BFS 한 번으로 창고 외부와 연결돼 지게차로 접근 가능한 모든 컨테이너 출고 가능
        
        크레인으로 출고
            - 이중 반복문으로 간단하게 처리가능
            - 출고된 컨테이너 수 리턴
            - 출고된 위치는 빈 공간으로 전환
    */
    
    private static final int PADDING_SIZE = 2;
    private static final char EMPTY = '.';
    
    private int r, c;
    private char[][] storage;
    
    public int solution(String[] storage, String[] requests) {
        init(storage);
        
        int answer = storage.length * storage[0].length();
        int delivery = 0;
        for (String request : requests) {
            char containerType = request.charAt(0);
            answer -= request.length() == 1 ? 
                deliveryByForklift(containerType) : deliveryByCrane(containerType);
        }
        
        return answer;
    }
    
    private void init(String[] storage) {
        r = storage.length + PADDING_SIZE;
        c = storage[0].length() + PADDING_SIZE;
        this.storage = new char[r][c];
        
        for (int y = 0 ; y < r; y++) {
            Arrays.fill(this.storage[y], EMPTY);
        }
        
        for (int y = 1; y < r - 1; y++) {
            for (int x = 1; x < c - 1; x++) {
                this.storage[y][x] = storage[y - 1].charAt(x - 1);
            }
        }
    }
    
    // 지게차로 접근할 수 있는 컨테이너 중 containerType에 해당하는 컨테이너를 출고
    // 출고한 컨테이너 수 리턴
    private int deliveryByForklift(char containerType) {
        int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        int delivery = 0; // 출고된 컨테이너 수
        
        Queue<int[]> que = new ArrayDeque<>();
        boolean[][] visit = new boolean[r][c];
        
        que.add(new int[] {0, 0});
        visit[0][0] = true;
        
        while (!que.isEmpty()) {
            int[] cur = que.poll();
            
            for (int[] d : dir) {
                int nr = cur[0] + d[0];
                int nc = cur[1] + d[1];
                
                if (isValidRange(nr, nc) && !visit[nr][nc]) {
                    if (storage[nr][nc] == EMPTY) {
                        que.add(new int[] {nr, nc});
                        visit[nr][nc] = true;
                    } else if (storage[nr][nc] == containerType) {
                        storage[nr][nc] = EMPTY;
                        delivery += 1;
                        visit[nr][nc] = true;
                    }
                }
            }
        }
        
        return delivery;
    }
    
    // 크레인로 접근할 수 있는 컨테이너 중 containerType에 해당하는 컨테이너를 출고
    // 출고한 컨테이너 수 리턴
    private int deliveryByCrane(char containerType) {
        int delivery = 0;
        
        for (int y = 1; y < r - 1; y++) {
            for (int x = 1; x < c - 1; x++) {
                if (storage[y][x] == containerType) {
                    storage[y][x] = EMPTY;;
                    delivery += 1;
                }
            }
        }
        
        return delivery;
    }
    
    private boolean isValidRange(int y, int x) {
        return 0 <= y && y < r && 0 <= x && x < c;
    }
}