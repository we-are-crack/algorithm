import java.util.*;

class Solution {
    
    private List<String> allMenuComb = new ArrayList<>();
    
    public String[] solution(String[] orders, int[] course) {
        // 존재하는 모든 메뉴
        Set<Character> menuSet = new HashSet<>();
        for (String order : orders) {
            for (char menu : order.toCharArray()) {
                menuSet.add(menu);
            }
        }
        
        // 메뉴를 리스트로 만들고 출력시 메뉴 구성은 사전 순으로 오름차순이므로
        // 미리 사전 순 오름차순으로 정렬
        // 조합 계산 시 자동으로 오름차순으로 조합 만들어짐
        List<Character> menu = new ArrayList<>(menuSet);
        Collections.sort(menu);
        
        for (int c : course) {
            combination(menu, new char[c], 0, 0);
        }
        
        // 각 메뉴 조합이 몇번 사용되었는지 계산하기 위한 자료구조
        // TreeMap -> Key에 대해 정렬된 구조 -> key = 해당 조합이 전체 메뉴에 대해 가장 많이 포함된 횟수, value = 메뉴 조합 문자열 리스트
        // TreeMap.lastKey() = 전체 메뉴에 대해 가장 많이 포함된 횟수
        Map<Integer, TreeMap<Integer, List<String>>> usedCount = new HashMap<>();
        for (int c : course) {
            usedCount.put(c, new TreeMap<>());
        }
        
        // 각 주문에 메뉴 조합이 몇 번 포함되었는지 계산하기 위해
        // 미리 주문 문자열을 숫자로 변환
        List<Integer> ordersInt = new ArrayList<>();
        for (String order : orders) {
            ordersInt.add(toInt(order));
        }
        
        for (String menuComb : allMenuComb) {
            int cnt = 0;
            int menuCombInt = toInt(menuComb); // 메뉴 조합을 숫자로 변환
            for (int orderInt : ordersInt) {
                if (isIncluded(orderInt, menuCombInt)) { // 주문에 해당 메뉴 조합이 포함되어 있으면
                    cnt++;
                }
            }
            
            if (cnt >= 2) { // 포함된 횟수가 2회 이상이면
                usedCount.get(menuComb.length())
                    .computeIfAbsent(cnt, k -> new ArrayList<>()).add(menuComb);
            }
        }
        
        List<String> answer = new ArrayList<>();
        for (int c : course) {
            TreeMap<Integer, List<String>> v = usedCount.get(c);
            if (v.isEmpty()) {
                continue;
            }
            
            // c 개를 메뉴를 사용한 메뉴 조합에서 주문에 가장 많이 포함된 메뉴 조합을 추가
            answer.addAll(v.get(v.lastKey()));
        }
        
        Collections.sort(answer);
        return answer.toArray(new String[0]);
    }
    
    /**
     모든 메뉴 조합 계산
     */
    private void combination(List<Character> menu, char[] sel, int selCnt, int idx) {
        if (selCnt == sel.length) {
            allMenuComb.add(new String(sel));
            return;
        }
        
        if (idx == menu.size()) {
            return;
        }
        
        combination(menu, sel, selCnt, idx + 1);
        
        sel[selCnt] = menu.get(idx);
        combination(menu, sel, selCnt + 1, idx + 1);
    }
    
    /**
     문자열을 int형 숫자로 바꾸는 함수
     모든 문자는 대문자 알파벳으로만 이루어져 있으므로 최대 26개 -> 비트마스킹으로 표현 가능
     */
    private int toInt(String str) {
        int bitmask = 0;
        for (char ch : str.toCharArray()) {
            bitmask |= 1 << ch - 'A';
        }
        
        return bitmask;
    }
    
    /**
     return if (b가 a에 모두 포함) true else false
     */
    private boolean isIncluded(int a, int b) {
        return (a & b) == b;
    }
}