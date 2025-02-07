import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int p = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<GameRoom> rooms = new ArrayList<>();

        while (p-- > 0) {
            st = new StringTokenizer(br.readLine());
            User user = new User(Integer.parseInt(st.nextToken()), st.nextToken());

            boolean entered = false;
            for (int i = 0; i < rooms.size() && !entered; i++) {
                entered = rooms.get(i).enter(user);
            }

            if (!entered) {
                GameRoom gameRoom = new GameRoom(m, user.level);
                gameRoom.enter(user);
                rooms.add(gameRoom);
            }
        }

        StringBuilder answer = new StringBuilder();
        for (GameRoom room : rooms) {
            answer.append(room);
        }

        System.out.print(answer);
    }

    private static class GameRoom {
        private int limit; // 방 정원
        private int level; // 입장 기준 레벨
        private PriorityQueue<User> participant = new PriorityQueue<>();

        public GameRoom(int limit, int level) {
            this.limit = limit;
            this.level = level;
        }

        public boolean enter(User user) {
            if (isStarted() || user.level < level - 10 || level + 10 < user.level) {
                return false;
            }

            participant.add(user);
            return true;
        }

        public boolean isStarted() {
            return limit == participant.size();
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(isStarted() ? "Started!" : "Waiting!").append("\n");
            while (!participant.isEmpty()) {
                sb.append(participant.poll()).append("\n");
            }
            return sb.toString();
        }
    }

    private static class User implements Comparable<User> {
        private int level;
        private String name;

        public User(int level, String name) {
            this.level = level;
            this.name = name;
        }

        @Override
        public String toString() {
            return level + " " + name;
        }

        @Override
        public int compareTo(User o) {
            return name.compareTo(o.name);
        }
    }
}
