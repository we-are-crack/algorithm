import java.util.*;

class Solution {
    
    private static class Music implements Comparable<Music> {
        int id;
        int playCount;
        
        public Music(int id, int playCount) {
            this.id = id;
            this.playCount = playCount;
        }
        
        @Override
        public int compareTo(Music other) {
            if (this.playCount != other.playCount) {
                return other.playCount - this.playCount;
            }
            
            return this.id - other.id;
        }
    }
    
    public int[] solution(String[] genres, int[] plays) {
        Map<String, Integer> playCountByGenre = new HashMap<>();
        Map<String, PriorityQueue<Music>> scoreByGenre = new HashMap<>();
        
        int n = genres.length;
        for (int i = 0; i < n; i++) {
            String genre = genres[i];
            int play = plays[i];
            
            playCountByGenre.put(genre, playCountByGenre.getOrDefault(genre, 0) + play);
            
            if (!scoreByGenre.containsKey(genre)) {
                scoreByGenre.put(genre, new PriorityQueue<>());
            }
            scoreByGenre.get(genre).add(new Music(i, play));
        }
        
        Set<Map.Entry<String, Integer>> sortedByPlayCount = new TreeSet(Map.Entry.comparingByValue(Comparator.reverseOrder()));
        for (Map.Entry<String, Integer> entry : playCountByGenre.entrySet()) {
            sortedByPlayCount.add(entry);
        }
        
        List<Integer> temp = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : sortedByPlayCount) {
            PriorityQueue<Music> score = scoreByGenre.get(entry.getKey());
            
            int addCount = 0;
            while (addCount < 2 && !score.isEmpty()) {
                temp.add(score.poll().id);
                addCount++;
            }
        }
        
        int[] answer = new int[temp.size()];
        for (int i = 0; i < temp.size(); i++) {
            answer[i] = temp.get(i);
        }
        
        return answer;
    }
}