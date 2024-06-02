class Solution {
    public int[] solution(int n) {
        int totalPyramids = calculatePyramid(n);
        int[] answer = new int[totalPyramids];
        int pyramid = 0;
        int rotation = 0;
        while(pyramid != totalPyramids) {
            int goingTime = n - 1 - (3 * rotation);
            if(pyramid == totalPyramids - 1) {
                down(pyramid, answer, rotation, 1);
                return answer;
            }
            pyramid = down(pyramid, answer, rotation, goingTime);
            pyramid = right(pyramid, answer, rotation, goingTime, n);
            pyramid = up(pyramid, answer, rotation, goingTime, n);
            rotation++;
        }
        return answer;
    }
    private int calculatePyramid(int n) {
        return n*(n+1)/2;
    }
    private int down(int pyramid, int[] answer, int rotation, int goingTime){
        int startIndex = calculatePyramid(rotation*2) + rotation;
        for(int i = 0 ; i < goingTime; i++){
            answer[startIndex] = ++pyramid;
            startIndex += 2 * rotation + 1 + i;
        }
        return pyramid;
    }
    private int right(int pyramid, int[] answer, int rotation, int goingTime, int n){
        int startIndex = calculatePyramid(n-1-rotation) + rotation;
        for(int i = 0 ; i < goingTime; i++){
            answer[startIndex+i] = ++pyramid;
        }
        return pyramid;
    }
    private int up(int pyramid, int[] answer, int rotation, int goingTime, int n){
        int startIndex = calculatePyramid(n-rotation) - rotation - 1;
        for(int i = 0 ; i < goingTime; i++){
            answer[startIndex] = ++pyramid;
            startIndex -= n-rotation-i;
        }
        return pyramid;
    }
}