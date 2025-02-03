import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        StringBuilder answer = new StringBuilder();

        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken()); // 팀 개수
            int k = Integer.parseInt(st.nextToken()); // 문제 개수
            int id = Integer.parseInt(st.nextToken()); // 팀 ID
            int m = Integer.parseInt(st.nextToken()); // 로그 엔트리 수

            Team[] teams = new Team[n + 1];
            for (int i = 1; i <= n; i++) {
                teams[i] = new Team(k);
            }

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int teamID = Integer.parseInt(st.nextToken()); // 제출 팀 ID
                int pNum = Integer.parseInt(st.nextToken()); // 문제 번호
                int s = Integer.parseInt(st.nextToken()); // 획득한 점수

                teams[teamID].updateScore(pNum, s, i);
            }

            int rank = 1;
            for (int i = 1; i <= n; i++) {
                rank += teams[id].compareTo(teams[i]);
            }

            answer.append(rank).append("\n");
        }

        System.out.print(answer);
    }

    private static class Team {

        private int[] score;
        private int submitCount;
        private int lastSubmitTime;

        public Team(int k) {
            score = new int[k + 1];
        }

        public void updateScore(int pNum, int s, int submitTime) {
            if (score[pNum] < s) {
                score[pNum] = s;
            }
            submitCount++;
            lastSubmitTime = submitTime;
        }

        public int getTotalScore() {
            int totalScore = 0;
            for (int s : score) {
                totalScore += s;
            }
            return totalScore;
        }

        /**
         * @param otherTeam 비교 대상 팀
         * @return otherTeam 가 현재 팀보다 뛰어나다면 1, 그외 0
         */
        public int compareTo(Team otherTeam) {
            if (this.getTotalScore() != otherTeam.getTotalScore()) {
                return this.getTotalScore() < otherTeam.getTotalScore() ? 1 : 0;
            }

            if (this.submitCount != otherTeam.submitCount) {
                return this.submitCount > otherTeam.submitCount ? 1 : 0;
            }

            return this.lastSubmitTime > otherTeam.lastSubmitTime ? 1 : 0;
        }
    }
}
