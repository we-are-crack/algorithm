import java.util.*;

class Solution {
    static String[] LANGUAGES = {"cpp", "java", "python", "-"};
    static String[] OCCUPATIONS = {"backend", "frontend", "-"};
    static String[] CAREERS = {"junior", "senior", "-"};
    static String[] FOODS = {"chicken", "pizza", "-"};
    static HashMap<String, ArrayList<Integer>> applicants = new HashMap<>();

    public int[] solution(String[] info, String[] query) {
        getAllInfo();
        int[] answer = new int[query.length];
        for (String s : info) {
            String[] information = s.split(" ");
            String[] keys = getKeys(information);
            for (String key : keys) {
            applicants.get(key).add(Integer.parseInt(information[4]));
            }
        }
        for(ArrayList<Integer> list : applicants.values()) {
            Collections.sort(list);
        }
        for(int i = 0; i < query.length; i++) {
            String[] conditions = query[i].split(" and | ");
            String key = conditions[0] + " " + conditions[1] + " " + conditions[2] + " " + conditions[3];
            int targetScore = Integer.parseInt(conditions[4]);
            answer[i] = applicants.get(key).size() - binarySearch(applicants.get(key), targetScore);
        }
        return answer;
    }
    private int binarySearch(ArrayList<Integer> scores, int target) {
        int start = 0, end = scores.size() - 1;
        int mid;
        while(start <= end) {
            mid = (end + start) / 2;
            if(scores.get(mid) >= target) {
                end = mid - 1;
            }
            else start = mid + 1;
        }
        return start;
    }
    private void getAllInfo() {
        for(String l : LANGUAGES) {
            for(String o : OCCUPATIONS) {
                for(String c : CAREERS) {
                    for(String f : FOODS) {
                        String key = l + " " + o + " " + c + " " + f;
                        applicants.put(key, new ArrayList<>());
                    }
                }
            }
        }
    }
    private String[] getKeys(String[] information) {
        String[] keys = new String[16];
        int i = 0;
        for(String l : new String[]{information[0], "-"}) {
            for(String o : new String[]{information[1], "-"}){
                for(String c : new String[]{information[2], "-"}){
                    for(String f : new String[]{information[3], "-"}){
                        keys[i++] = l + " " + o + " " + c + " " + f;
                    }
                }
            }
        }
        return keys;
    }
}