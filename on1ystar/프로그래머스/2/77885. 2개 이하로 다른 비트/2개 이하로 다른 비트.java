class Solution {
    public long[] solution(long[] numbers) {
        long[] answer = new long[numbers.length];
        int answerIndex = 0;

        for (long number : numbers) {
            StringBuilder binaryString = new StringBuilder(Long.toBinaryString(number));
            binaryString.insert(0, '0');

            for (int i = binaryString.length() - 1; i >= 0; i--) {

                if (binaryString.charAt(i) == '0') {
                    binaryString.setCharAt(i, '1');

                    if (i != binaryString.length() - 1) {
                        binaryString.setCharAt(i + 1, '0');
                    }

                    break;
                }
            }
            answer[answerIndex++] = Long.parseLong(binaryString.toString(), 2);
        }

        return answer;
    }
}