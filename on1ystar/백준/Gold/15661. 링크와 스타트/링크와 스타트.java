import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private static int n;
    private static int[][] arr;
    private static int minPowerGap = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 1; j <= n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i <= n / 2; i++) {
            makeStartTeam(i, 0, new ArrayList<>());
        }

        System.out.println(minPowerGap);
    }

    private static void makeStartTeam(int size, int start, List<Integer> startTeam) {
        if (size == startTeam.size()) {
            int startTeamPower = getTeamPower(startTeam);
            int linkTeamPower = getTeamPower(getLinkTeam(startTeam));
            minPowerGap = Math.min(minPowerGap, Math.abs(startTeamPower - linkTeamPower));
            return;
        }

        for (int i = start; i <= n; i++) {
            startTeam.add(i);
            makeStartTeam(size, i + 1, startTeam);
            startTeam.remove(startTeam.size() - 1);
        }
    }

    private static List<Integer> getLinkTeam(List<Integer> startTeam) {
        List<Integer> linkTeam = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            if (!startTeam.contains(i)) {
                linkTeam.add(i);
            }
        }

        return linkTeam;
    }

    private static int getTeamPower(List<Integer> member) {
        int teamPower = 0;

        if (member.size() == 1) {
            return teamPower;
        }

        for (int i = 0; i < member.size() - 1; i++) {
            for (int j = i + 1; j < member.size(); j++) {
                teamPower += arr[member.get(i)][member.get(j)];
                teamPower += arr[member.get(j)][member.get(i)];
            }
        }

        return teamPower;
    }
}