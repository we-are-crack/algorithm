import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        Item[] items = new Item[n];
        Backpack[] backpacks = new Backpack[k + 1];  // index = total weight
        int maxValue = 0;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            items[i] = new Item(i, w, v);
        }

        Arrays.sort(items, Comparator.comparingInt(o -> o.weight));
        backpacks[0] = new Backpack();

        for (int i = 1; i <= k; i++) {
            backpacks[i] = new Backpack();

            for (int j = 0; j < n; j++) {
                if (items[j].weight > i) { break; }

                Backpack preBackpack = backpacks[i - items[j].weight];

                if (!preBackpack.contains(items[j]) && backpacks[i].totalValue < preBackpack.totalValue + items[j].value) {
                    backpacks[i] = new Backpack(preBackpack.items);
                    backpacks[i].addItem(items[j]);
                }
            }

            maxValue = Math.max(maxValue, backpacks[i].totalValue);
        }

//        for (int i = 1; i <= k; i++) {
//            System.out.println("backpacks[" + i + "] = " + backpacks[i].totalValue);
//        }

        System.out.println(maxValue);
    }

    static class Item {

        private final int id;
        private final int weight;
        private final int value;

        public Item(int id, int weight, int value) {
            this.id = id;
            this.weight = weight;
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof Item) {
                return this.id == ((Item) o).id;
            }

            return false;
        }

        @Override
        public int hashCode() {
            return id;
        }
    }

    static class Backpack {

        private final List<Item> items;
        private int totalValue;

        public Backpack() {
            this.items = new ArrayList<>();
            this.totalValue = 0;
        }

        public Backpack(List<Item> items) {
            this.items = new ArrayList<>(items);
            for (Item item : items) {
                totalValue += item.value;
            }
        }

        public void addItem(Item item) {
            items.add(item);
            totalValue += item.value;
        }

        public boolean contains(Item item) {
            return items.contains(item);
        }
    }
}