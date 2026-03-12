import java.util.*;

/**
    테스트케이스
    [1, 0, 1], ["iron", "iron", "iron", "iron", "iron", "diamond"]일 때 답은 26이 나와야함
        => 돌 곡괭이 먼저 사용 후 다이아 곡괭이 사용: 5 + 5 + 5 + 5 + 5 + 1 = 26
    
    아래 코드는 다이아 곡괭이 먼저 사용 후 돌 곡괭이 사용: 1 + 1 + 1 + 1 + 1 + 25 = 30
    하지만 정답 처리됌. 문제있음.
        
*/

class Solution {
    
    public int solution(int[] picks, String[] minerals) {
        int pickCount = 0;
        for (int p : picks) pickCount += p;
        
        List<Chunk> chunks = new ArrayList<>();
        Chunk chunk = new Chunk();
        for (int i = 0; i < minerals.length; i++) {
            if (chunks.size() == pickCount) {
                break;
            }
            
            chunk.add(minerals[i]);
            
            if (chunk.size == 5 || i == minerals.length - 1) {
                chunks.add(chunk);
                chunk = new Chunk();
            }
        }
        
        chunks.sort(Comparator.comparingInt((Chunk c) -> c.value).reversed());
        
        int answer = 0, pickType = 0;
        for (Chunk c : chunks) {
            while (picks[pickType] == 0) {
                pickType++;
            }
            
            answer += c.getRequiredFatigue(pickType);
            picks[pickType]--;
        }
        
        return answer;
    }
    
    private static class Chunk {
        private static int DIAMOND = 0, IRON = 1, STONE = 2;
        private static int[][] fatigue = {
            {1, 1, 1}, // dia
            {5, 1, 1}, // iron
            {25, 5, 1} // stone
        };
        
        private int[] minerals = new int[3]; // dia, iron, stone 순
        private int value = 0;
        int size = 0;
        
        public void add(String mineral) {
            int mType = STONE;
            if ("diamond".equals(mineral)) mType = DIAMOND;
            else if ("iron".equals(mineral)) mType = IRON;
            
            minerals[mType]++;
            value += fatigue[STONE][mType];
            size++;
        }
        
        public int getRequiredFatigue(int pick) {
            int fat = 0;
            for (int i = 0; i < minerals.length; i++) {
                fat += minerals[i] * fatigue[pick][i];
            }
            
            return fat;
        }
    }
}