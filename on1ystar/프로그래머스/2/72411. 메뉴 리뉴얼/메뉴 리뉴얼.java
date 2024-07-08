import java.util.*;

class Solution {

    ArrayList<String> courseCandidates = new ArrayList<>();
    int maxCourseCnt = 0;

    public String[] solution(String[] orders, int[] course) {
        ArrayList<String> courseList = new ArrayList<>();
        HashSet<String> single = new HashSet<>();
        for (String order : orders) {
            single.addAll(List.of(order.split("")));
        }
        for(int courseSize : course) {
            HashMap<String, Integer> map = new HashMap<>();
            combination(new ArrayList<>(single), new boolean[single.size()], courseSize, new StringBuilder());
            for(String courseCandidate : courseCandidates) {
                int cnt = 0;
                for(String order : orders) {
                    boolean isContain = true;
                    for(int i = 0; i < courseCandidate.length(); i++) {
                        if(!(order.contains(courseCandidate.charAt(i) + ""))) {
                            isContain = false;
                            break;
                        }
                    }
                    if(isContain) cnt++;
                }
                map.put(courseCandidate, cnt);
                maxCourseCnt = Math.max(maxCourseCnt, cnt);
            }
            map.forEach((courseCandidate, cnt) -> {
                if(cnt == maxCourseCnt && maxCourseCnt >= 2) courseList.add(courseCandidate);
            });
            maxCourseCnt = 0;
            courseCandidates.clear();
        }
        Collections.sort(courseList);
        return courseList.toArray(new String[0]);
    }

    private void combination(ArrayList<String> singles, boolean[] visit, int size, StringBuilder comb) {
        if(size == 0)
            courseCandidates.add(comb.toString());
        else {
            for(int i = 0; i < singles.size(); i++) {
                if(visit[i]) continue;
                visit[i] = true;
                comb.append(singles.get(i));
                combination(singles, visit.clone(), size - 1, comb);
                comb.deleteCharAt(comb.length() - 1);
            }
        }
    }
}