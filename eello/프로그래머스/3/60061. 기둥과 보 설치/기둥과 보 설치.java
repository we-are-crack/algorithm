import java.util.*;

class Solution {
    
    private static final int PILLAR = 0, BEAM = 1;
    private static final int DELETE = 0, INSTALL = 1;
    
    private int installedCount;
    private boolean[][][] map;
        
    public int[][] solution(int n, int[][] build_frame) {
        installedCount = 0;
        map = new boolean[n + 3][n + 3][2];
        
        for(int[] frame : build_frame) {
            int x = frame[0] + 1, y = frame[1] + 1, type = frame[2], oper = frame[3];
            
            if (type == PILLAR) {
                if (oper == INSTALL) installPillar(y, x);
                else deletePillar(y, x);
            } else {
                if (oper == INSTALL) installBeam(y, x);
                else deleteBeam(y, x);
            }
        }
                
        int[][] answer = new int[installedCount][];
        int idx = 0;
        for (int x = 1; x <= n + 1; x++) {
            for (int y = 1; y <= n + 1; y++) {
                for (int t = 0; t < 2; t++) {
                    if (map[y][x][t]) {
                        answer[idx++] = new int[] {x - 1, y - 1, t};
                    }
                }
            }
        }
        
        return answer;
    }
    
    private void installPillar(int y, int x) {
        if (isValidatePillar(y, x)) {
            map[y][x][PILLAR] = true;
            installedCount++;
        }
    }
    
    private boolean isValidatePillar(int y, int x) {
        return y == 1 // 바닥이거나
            || map[y - 1][x][PILLAR] // 기둥 위에 존재하거나
            || map[y][x][BEAM] || map[y][x - 1][BEAM]; // 보의 한쪽 끝에 있는 경우
    }
    
    private void deletePillar(int y, int x) {
        map[y][x][PILLAR] = false;
        installedCount--;
        
        boolean isDeletable = true;
        if (isDeletable && map[y + 1][x - 1][BEAM]) {
            isDeletable &= isValidateBeam(y + 1, x - 1);
        }
        
        if (isDeletable && map[y + 1][x][PILLAR]) {
            isDeletable &= isValidatePillar(y + 1, x);
        }
        
        if (isDeletable && map[y + 1][x][BEAM]) {
            isDeletable &= isValidateBeam(y + 1, x);
        }
        
        if (!isDeletable) {
            map[y][x][PILLAR] = true;
            installedCount++;
        }
    }
    
    private void installBeam(int y, int x) {
        if (isValidateBeam(y, x)) {
            map[y][x][BEAM] = true;
            installedCount++;
        }
    }
    
    private boolean isValidateBeam(int y, int x) {
        return map[y - 1][x][PILLAR] || map[y - 1][x + 1][PILLAR] // 보의 한 쪽 끝 부분이 기둥 위에 있거나
            || (map[y][x - 1][BEAM] && map[y][x + 1][BEAM]); // 보의 양쪽이 다른 보와 연결된 경우
    }
    
    private void deleteBeam(int y, int x) {
        map[y][x][BEAM] = false;
        installedCount--;
        
        boolean isDeletable = true;
        if (isDeletable && map[y][x - 1][BEAM]) {
            isDeletable &= isValidateBeam(y, x - 1);
        }
        
        if (isDeletable && map[y][x + 1][BEAM]) {
            isDeletable &= isValidateBeam(y, x + 1);
        }
        
        if (isDeletable && map[y][x][PILLAR]) {
            isDeletable &= isValidatePillar(y, x);
        }
        
        if (isDeletable && map[y][x + 1][PILLAR]) {
            isDeletable &= isValidatePillar(y, x + 1);
        }
        
        if (!isDeletable) {
            map[y][x][BEAM] = true;
            installedCount++;
        }
    }
}