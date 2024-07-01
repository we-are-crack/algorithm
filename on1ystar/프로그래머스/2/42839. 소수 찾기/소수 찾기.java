import java.util.*;

class Solution {
    public int solution(String numbers) {
        int answer = 0;
        HashSet<Integer> primeNumbers = new HashSet<>();
        HashSet<Integer> permutations = new HashSet<>();
        for(int i = 1; i <= numbers.length(); i++) {
            permute(new ArrayList<String>(List.of(numbers.split(""))), i, "", permutations);
        }
        permutations.removeIf(e -> !isPrime(e));
        return permutations.size();
    }

    private boolean isPrime(int number) {
        if(number == 1 || number == 0) return false;
        if(number == 2) return true;
        for(int i = 2; i < number; i++) {
            if(number % i == 0) return false;
        }
        return true;
    }

    private void permute(ArrayList<String> numberPieces, int size, String resultNumber, HashSet<Integer> permutations) {
        if(size == 0) permutations.add(Integer.parseInt(resultNumber));
        for(int i = 0; i < numberPieces.size(); i++) {
            ArrayList<String> temp = new ArrayList<String>(numberPieces);
            temp.remove(i);
            permute(temp, size - 1, resultNumber + numberPieces.get(i), permutations);
        }
    }
}