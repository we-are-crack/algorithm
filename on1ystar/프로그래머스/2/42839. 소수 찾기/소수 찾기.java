import java.util.*;

class Solution {
    public int solution(String numbers) {
        int answer = 0;
        ArrayList<Integer> primeNumbers = new ArrayList<>();
        ArrayList<Integer> permutations = new ArrayList<>();
        for(int i = 1; i <= numbers.length(); i++) {
            permute(new ArrayList<String>(List.of(numbers.split(""))), i, "", permutations);
        }
        Set<Integer> permutationsSet = new HashSet<>(permutations);
        System.out.println("permutationsSet = " + permutationsSet);
        for(int permutation : permutationsSet) {
            if(isPrime(permutation)) {
                primeNumbers.add(permutation);
            }
        }
        System.out.println("primeNumbers = " + primeNumbers);
        return primeNumbers.size();
    }

    private boolean isPrime(int number) {
        if(number == 1 || number == 0) return false;
        if(number == 2) return true;
        for(int i = 2; i < number; i++) {
            if(number % i == 0) return false;
        }
        return true;
    }

    private void permute(ArrayList<String> numberPieces, int size, String resultNumber, ArrayList<Integer> permutations) {
        if(size == 0) permutations.add(Integer.parseInt(resultNumber));
        for(int i = 0; i < numberPieces.size(); i++) {
            ArrayList<String> temp = new ArrayList<String>(numberPieces);
            temp.remove(i);
            permute(temp, size - 1, resultNumber + numberPieces.get(i), permutations);
        }
    }
}