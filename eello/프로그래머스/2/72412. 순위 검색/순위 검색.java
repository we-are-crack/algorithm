class Solution {
    
    private static String[][] key = {
		{"cpp", "java", "python"},
		{"backend", "frontend"},
		{"junior", "senior"},
		{"chicken", "pizza"}
	};

	private int[][][][][] scores;

	public int[] solution(String[] info, String[] query) {
		init(info);

		int[] answer = new int[query.length];
		int idx = 0;
		for (String q : query) {
			String[] condition = q.split(" and ");
			String[] foodAndScore = condition[condition.length - 1].split(" ");
			condition[condition.length - 1] = foodAndScore[0];
			int score = Integer.parseInt(foodAndScore[1]);

			answer[idx++] = executeQuery(condition, score, new int[condition.length], 0);
		}
		
		return answer;
	}

	private void init(String[] info) {
		scores = new int[key[0].length][key[1].length][key[2].length][key[3].length][100_001];
		for (String inf : info) {
			String[] condition = inf.split(" ");
			int lang = getIndex(0, condition[0]);
			int job = getIndex(1, condition[1]);
			int career = getIndex(2, condition[2]);
			int food = getIndex(3, condition[3]);
			int score = Integer.parseInt(condition[4]);

			scores[lang][job][career][food][score] += 1;
		}

		for (int i = 0; i < key[0].length; i++) {
			for (int j = 0; j < key[1].length; j++) {
				for (int k = 0; k < key[2].length; k++) {
					for (int l = 0; l < key[3].length; l++) {
						for (int m = 99_999; m >= 0; m--) {
							scores[i][j][k][l][m] += scores[i][j][k][l][m + 1];
						}
					}
				}
			}
		}
	}

	private int executeQuery(String[] condition, int score, int[] idxs, int k) {
		if (k == condition.length) {
			return scores[idxs[0]][idxs[1]][idxs[2]][idxs[3]][score];
		}

		int result = 0;
		if ("-".equals(condition[k])) {
			for (int i = 0; i < key[k].length; i++) {
				idxs[k] = i;
				result += executeQuery(condition, score, idxs, k + 1);
			}
		} else {
			idxs[k] = getIndex(k, condition[k]);
			result = executeQuery(condition, score, idxs, k + 1);
		}

		return result;
	}

	private int getIndex(int type, String target) {
		for (int i = 0; i < key[type].length; i++) {
			if (target.equals(key[type][i])) {
				return i;
			}
		}
		return -1;
	}
}