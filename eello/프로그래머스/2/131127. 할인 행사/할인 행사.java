class Solution {
    
    private int satisfactionCount = 0;
    
    public int solution(String[] want, int[] number, String[] discount) {
        int r = 0;
        while (r < 10) {
            deal(want, number, discount[r++], -1);
        }
                
        int l = 0, answer = satisfactionCount == want.length ? 1 : 0;
        while (r < discount.length) {
            deal(want, number, discount[l++], 1); // refund
            deal(want, number, discount[r++], -1); // buy
            
            if (satisfactionCount == want.length) {
                answer++;
            }
        }
        
        return answer;
    }
    
    private void deal(String[] want, int[] number, String product, int type) {
        int index = getWantIndex(want, product);
        if (index != -1) {
            if (number[index] == 0) satisfactionCount--;
            number[index] += type;
            if (number[index] == 0) satisfactionCount++;
        }
    }
    
    private int getWantIndex(String[] want, String product) {
        for (int i = 0; i < want.length; i++) {
            if (product.equals(want[i])) {
                return i;
            }
        }
        return -1;
    }
}