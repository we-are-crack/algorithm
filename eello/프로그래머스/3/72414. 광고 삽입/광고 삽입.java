class Solution {
    
    public String solution(String play_time, String adv_time, String[] logs) {
		int playTime = convert(play_time);
		int advTime = convert(adv_time);

		int[] acc = new int[playTime + 1];
		long[] sum = new long[playTime + 1];

		for (int i = 0; i < logs.length; i++) {
			String[] split = logs[i].split("-");
			int start = convert(split[0]);
			int end = convert(split[1]);

			acc[start] += 1;
			acc[end] -= 1;
		}

		sum[0] = acc[0];
		for (int i = 1; i < acc.length; i++) {
			acc[i] += acc[i - 1];
			sum[i] = sum[i - 1] + acc[i];
		}

		int start = 1, end = start + advTime;
		int advStartTime = 0;
		long maxViewTime = sum[advTime - 1];
		while (end <= playTime) {
			long viewTime = sum[end - 1] - sum[start - 1];
			if (maxViewTime < viewTime) {
				maxViewTime = viewTime;
				advStartTime = start;
			}

			start++;
			end++;
		}

		return convert(advStartTime);
	}

	private int convert(String time) {
		String[] split = time.split(":");
		return Integer.parseInt(split[0]) * 3600
			+ Integer.parseInt(split[1]) * 60
			+ Integer.parseInt(split[2]);
	}

	private String convert(int time) {
		int h = time / 3600;
		time -= (3600 * h);

		int m = time / 60;
		time -= (60 * m);

		int s = time;

		return String.format("%02d:%02d:%02d", h, m, s);
	}
}