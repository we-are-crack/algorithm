import java.util.ArrayList;
import java.util.HashSet;

class Solution {
    public int solution(int N, int number) {
        ArrayList<HashSet<Integer>> countsOfN = new ArrayList<>();
        countsOfN.add(new HashSet<>());
        countsOfN.get(0).add(N);
        for(int i = 1; i <= 8; i++) {
            countsOfN.add(new HashSet<>());
            int j = 1;
            while(j < i) {
                countsOfN.get(i).addAll(calculate(countsOfN.get(j), countsOfN.get(i - j)));
                j++;
            }
            countsOfN.get(i).add(Integer.valueOf(String.valueOf(N).repeat(i)));
            if(countsOfN.get(i).contains(number)) return i;
        }
        return -1;
    }
    private HashSet<Integer> calculate(HashSet<Integer> set1, HashSet<Integer> set2) {
        HashSet<Integer> set = new HashSet<>();
        for(Integer n1 : set1) {
            for(Integer n2 : set2) {
                set.add(n1 + n2);
                set.add(n1 - n2);
                set.add(n1 * n2);
                if(n2 != 0) set.add(n1 / n2);
            }
        }
        return set;
    }
}