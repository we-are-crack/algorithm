class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int answer = 0;
        int maxHealth = health;
        int currentTime = 0;
        int successTime;
        int healAmount = 0;
        for(int[] attack : attacks) {
            successTime = 0;
            currentTime++;
            while (currentTime != attack[0]) {
                successTime++;
                if(successTime == bandage[0]) {
                    healAmount = bandage[1] + bandage[2];
                    successTime = 0;
                }
                else healAmount = bandage[1];
                health = Math.min(health + healAmount, maxHealth);
                currentTime++;
            }
            // 공격
            health -= attack[1];
            if(health <= 0) {
                answer = -1;
                break;
            }
        }
        return answer == -1 ? -1 : health;
    }
}