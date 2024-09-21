class Solution {
    
    public int solution(int[] bandage, int health, int[][] attacks) {
        int hp = health - attacks[0][1];
        int lastAttackTime = attacks[0][0];
        for (int i = 1; i < attacks.length; i++) {
            int attackTime = attacks[i][0];
            int term = attackTime - lastAttackTime - 1;
            
            hp = Math.min(
                hp + (bandage[1] * term) + (term / bandage[0] * bandage[2]),
                health
            );
            
            hp -= attacks[i][1];
            if (hp <= 0) {
                return -1;
            }
            
            lastAttackTime = attackTime;
        }
        
        return hp;
    }
}