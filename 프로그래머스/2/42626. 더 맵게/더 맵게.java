import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        //우선순위 큐
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        //default:  오름차순
        for(int i : scoville){
            pq.offer(i);
        }
        int result = 0;
        int firstFood = pq.poll();
        int secondFood = pq.poll();
        while(firstFood < K){
            int newFood = firstFood + secondFood*2;
            pq.offer(newFood);
            result++;
            if(pq.size()<2){
                firstFood = pq.poll();
                break;
            }
            firstFood = pq.poll();
            secondFood = pq.poll();
        }
        if(firstFood<K){
            return -1;
        }
        return result;
    }
}