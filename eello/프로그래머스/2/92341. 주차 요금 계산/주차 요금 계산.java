import java.util.*;

class Solution {
    
    public int[] solution(int[] fees, String[] records) {
        Map<String, List<String>> rec = new HashMap<>();
        for (String record : records) {
            String[] split = record.split(" ");
            String time = split[0];
            String carNumber = split[1];
            
            if (!rec.containsKey(carNumber)) {
                rec.put(carNumber, new ArrayList<>());
            }
            rec.get(carNumber).add(time);
        }
        
        Map<String, Integer> fee = new TreeMap<>();
        for (Map.Entry<String, List<String>> entry : rec.entrySet()) {
            fee.put(entry.getKey(), charge(fees, entry.getValue()));
        }
        
        int[] answer = new int[fee.size()];
        int idx = 0;
        for (Map.Entry<String, Integer> entry : fee.entrySet()) {
            answer[idx++] = entry.getValue();
        }
        
        return answer;
    }
    
    private int charge(int[] fees, List<String> record) {
        int parkingTime = 0;
        for (int i = 0; i < record.size(); i += 2) {
            String in = record.get(i);
            String out = i + 1 == record.size() ? "23:59" : record.get(i + 1);
            parkingTime += getParkingTime(in, out);
        }
        
        return calculateFee(fees, parkingTime);
    }
    
    private int calculateFee(int[] fees, int parkingTime) {
        if (parkingTime <= fees[0]) {
            return fees[1];
        }
        
        parkingTime -= fees[0];
        return fees[1] + (int) Math.ceil((double) parkingTime / fees[2]) * fees[3];
    }
    
    private int getParkingTime(String in, String out) {
        return convertToMinute(out) - convertToMinute(in);
    }
    
    private int convertToMinute(String time) {
        String[] split = time.split(":");
        return 60 * Integer.parseInt(split[0]) + Integer.parseInt(split[1]);
    }
}