import java.util.*;

class Solution {
    
    private static final int PRICE = 100;

	public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
		Member center = new Member();
		Member[] members = new Member[enroll.length];
		Map<String, Member> memberMap = new HashMap<>();
		for (int i = 0; i < enroll.length; i++) {
			Member member = new Member();
			members[i] = member;
			memberMap.put(enroll[i], member);
		}

		// setReferral
		for (int i = 0; i < referral.length; i++) {
			if ("-".equals(referral[i])) {
				members[i].setReferral(center);
			} else members[i].setReferral(memberMap.get(referral[i]));
		}

		for (int i = 0; i < seller.length; i++) {
			String sel = seller[i];
			int amt = amount[i];

			Member member = memberMap.get(sel);
			member.sell(amt);
		}

		int[] answer = new int[enroll.length];
		for (int i = 0; i < enroll.length; i++) {
			answer[i] = members[i].totalIncome;
		}

		return answer;
	}

	private static class Member {
		private Member referral;
		private int totalIncome;

		public void setReferral(Member referral) {
			this.referral = referral;
		}

		public void sell(int amount) {
			saveIncomeAndPayFee(PRICE * amount);
		}

		private void saveIncomeAndPayFee(int income) {
			int fee = (int)(income * 0.1);
			totalIncome += income - fee;
			if (fee != 0 && referral != null) {
				referral.saveIncomeAndPayFee(fee);
			}
		}
	}
}