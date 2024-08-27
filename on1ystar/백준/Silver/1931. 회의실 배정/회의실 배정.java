import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int answer = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        HashMap<Integer, ArrayList<Integer>> meetingsGraph = new HashMap<>();
        int allMeetingEndTime = 0;
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int startTime = Integer.parseInt(st.nextToken());  // start time
            int endTime = Integer.parseInt(st.nextToken());  // end time
            meetingsGraph.putIfAbsent(endTime, new ArrayList<>());
            meetingsGraph.get(endTime).add(startTime);
            allMeetingEndTime = Math.max(allMeetingEndTime, endTime);
        }

        int dp[] = new int[allMeetingEndTime + 1];
        for (int meetingEndTime = 0; meetingEndTime <= allMeetingEndTime; meetingEndTime++) {
            if(meetingsGraph.containsKey(meetingEndTime)) {
                int maxMeetings = 0;
                int startSameEnd = 0;
                boolean isExistStartDifferEnd = false;
                for (int startTime : meetingsGraph.get(meetingEndTime)) {
                    int dpMax = 0;
                    for (int i = 0; i <= startTime; i++) {
                        dpMax = Math.max(dpMax, dp[i]);
                    }

                    maxMeetings = Math.max(maxMeetings, dpMax);
                    if (startTime == meetingEndTime) {
                        startSameEnd++;
                        continue;
                    }

                    isExistStartDifferEnd = true;
                }

                dp[meetingEndTime] = maxMeetings + startSameEnd;
                if(isExistStartDifferEnd) {
                    dp[meetingEndTime]++;
                }
            }

            answer = Math.max(answer, dp[meetingEndTime]);
        }

        System.out.println(answer);
    }
}