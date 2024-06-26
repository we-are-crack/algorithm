class Solution {
    
    public boolean solution(int[][] key, int[][] lock) {
        lock = expand(lock, key.length - 1);
        for(int i = 0; i < 4; i++) {
            if (isOpen(lock, key)) {
               return true;
            }
            
            key = rotate(key);
        }

        return false;         
    }
    
    private int[][] expand(int[][] lock, int n) {
        // lock 주변으로 n만큼 확장
        int lockLen = lock.length;
        int len = lockLen + (2 * n);
        int[][] expanded = new int[len][len];
        
        for (int i = 0; i < lockLen; i++) {
            for (int j = 0; j < lockLen; j++) {
                expanded[i + n][j + n] = lock[i][j];
            }
        }
        
        return expanded;
    }
    
    private boolean isOpen(int[][] lock, int[][] key) {
        int range = lock.length - key.length + 1;
        for (int i = 0; i < range; i++) {
            for (int j = 0; j < range; j++) {
                if (isMatch(lock, key, i, j)) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    private boolean isMatch(int[][] lock, int[][] key, int r, int c) {
        // lock의 [r, c] 위치에 key [0, 0]을 맞춰 열쇠를 끼우는 경우
        int[][] copiedLock = copy(lock);
        
        int keyLen = key.length;
        for (int i = 0; i < keyLen; i++) {
            for (int j = 0; j < keyLen; j++) {
                copiedLock[i + r][j + c] += key[i][j];
            }
        }
        
        int lockLen = lock.length;
        for (int i = keyLen - 1; i <= lockLen - keyLen; i++) {
            for (int j = keyLen - 1; j <= lockLen - keyLen; j++) {
                if (copiedLock[i][j] != 1) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    private int[][] copy(int[][] original) {
        int len = original.length;
        int[][] clone = new int[len][len];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                clone[i][j] = original[i][j];
            }
        }
        
        return clone;
    }
    
    private int[][] rotate(int[][] key) {
        int keyLen = key.length;
        int[][] rotated = new int[keyLen][keyLen];
        for (int i = 0; i < keyLen; i++) {
            for (int j = 0; j < keyLen; j++) {
                rotated[i][j] = key[j][keyLen - i - 1];
            }
        }
        
        return rotated;
    }
}