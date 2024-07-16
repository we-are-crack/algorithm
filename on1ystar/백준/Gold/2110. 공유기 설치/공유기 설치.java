import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int house = scanner.nextInt();
        int wifi = scanner.nextInt();

        ArrayList<Integer> housePos = new ArrayList<>();
        for(int i = 0; i < house; i++) {
            housePos.add(scanner.nextInt());
        }
        Collections.sort(housePos);
        if(wifi == 2) {
            System.out.println(housePos.get(house - 1) - housePos.get(0));
            return;
        }
        int start = 1;
        int end = (housePos.get(house - 1) - housePos.get(0)) / (wifi - 1);
        int maxDistance = 0;
        while(start <= end) {
            int mid = (start + end) / 2;
            int wifiCount = settingWifi(housePos, mid);
            if(wifiCount >= wifi) {
                start = mid + 1;
                maxDistance = mid;
            }
            else end = mid - 1;
        }
        System.out.println(maxDistance);
    }
    static private int settingWifi(ArrayList<Integer> housePos, int distance) {
        int count = 1;
        int i = 0;
        int j = 1;
        while(j < housePos.size()) {
            if(housePos.get(j) - housePos.get(i) >= distance) {
                count++;
                i = j;
                j = i + 1;
                continue;
            }
            j++;
        }
        return count;
    }
}