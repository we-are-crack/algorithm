import java.util.ArrayList;

class Solution {
    public Object[] solution(int[] answers) {
        ArrayList<Integer> answer = new ArrayList<>();
        int[] students = new int[3];
        int[] no1 = {1, 2, 3, 4, 5};
        int[] no2 = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] no3 = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
        for(int i = 0; i < answers.length; i++) {
            if(answers[i] == no1[i % no1.length]) students[0]++;
            if(answers[i] == no2[i % no2.length]) students[1]++;
            if(answers[i] == no3[i % no3.length]) students[2]++;
        }
        int maxAnswer = Math.max(students[0], Math.max(students[1], students[2]));
        for(int i = 0; i < 3; i++) {
            if(maxAnswer == students[i])  answer.add(i+1);
        }
        return answer.toArray();
    }
}