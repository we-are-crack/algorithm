import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        StringBuilder answer = new StringBuilder();
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());

            int[] score = new int[n]; // score[i] = t => (i + 1)등의 팀 번호는 t
            Map<Integer, Integer> completed = new HashMap<>();
            for (int i = 0; i < n; i++) {
                score[i] = Integer.parseInt(st.nextToken());
                completed.put(score[i], completed.getOrDefault(score[i], 0) + 1);
            }

            int rank = 1;
            Map<Integer, Team> teams = new HashMap<>();
            for (int i = 0; i < n; i++) {
                if (completed.get(score[i]) < 6) {
                    continue;
                }

                Team team = teams.get(score[i]);
                if (team == null) {
                    team = new Team(score[i]);
                    teams.put(score[i], team);
                }

                team.complete(rank++);
            }

            PriorityQueue<Team> pq = new PriorityQueue<>();
            for (Team team : teams.values()) {
                pq.offer(team);
            }

            answer.append(pq.poll().getTeamId()).append("\n");
        }

        System.out.print(answer);
    }

    public static class Team implements Comparable<Team> {
        private int teamId; // 팀 번호
        private int completed; // 완주 인원
        private int score; // 상위 4명의 점수
        private int fifth; // 5등 성적

        public Team(int teamId) {
            this.teamId = teamId;
        }

        public void complete(int rank) {
            completed++;
            if (completed < 5) {
                score += rank;
            } else if (completed == 5) {
                fifth = rank;
            }
        }

        public int getTeamId() {
            return teamId;
        }

        @Override
        public int compareTo(Team o) {
            if (score != o.score) {
                return score - o.score;
            }

            return fifth - o.fifth;
        }
    }
}
