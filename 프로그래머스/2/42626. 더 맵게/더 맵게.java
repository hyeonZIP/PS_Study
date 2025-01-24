import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        //우선순위 큐
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        //default:  오름차순
        for(int i : scoville){
            pq.offer(i);
        }
        int answer = 0;
        
        while(pq.size()>1&& pq.peek() < K){
            int firstFood = pq.poll();
            int secondFood = pq.poll();
            
            int newFood = firstFood + secondFood*2;
            
            pq.offer(newFood);
            
            answer++;
        }
        if(pq.poll()<K){
            return -1;
        }
        return answer;
    }
}