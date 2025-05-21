import java.io.*;
import java.util.*;

/*
    멘토 n명 1~k번
    상담 유형 k개
    각 유형별로 한명씩 멘토 배정 후 나머지 경우의 수를 탐색??
    회의실 배정 문제와 비슷?
*/

class Solution {

    public int answer = Integer.MAX_VALUE;
    
    public int[] comb;
    public int restMento;
     
    public int solution(int k, int n, int[][] reqs) {
        
        // 멘토 배정 조합을 담을 배열
        comb = new int[k];
        
        // 모든 유형에 최소 한명의 멘토 배정
        for(int i=0; i<k; i++){
            comb[i]++;
        }
        
        restMento = n-k;
        
        combine(k,0,0, reqs);
        
        return answer;
    }
    
    private void combine(int k, int index, int depth, int[][] reqs){
        
        if(depth == restMento){
            
            sol(reqs);
            
        }else{
            for(int i=index; i<k; i++){
                comb[i]++;
                
                combine(k, i, depth+1, reqs);
                
                comb[i]--;
            }
        }
        
    }
    
    private void sol(int[][] reqs){
        
        PriorityQueue<Integer>[] thread = new PriorityQueue[comb.length];
        
        int time = 0;
        
        for(int i = 0; i < comb.length; i++){
            int threadCount = comb[i];
            thread[i] = new PriorityQueue<>();
            for(int ii = 0; ii < threadCount; ii++){
                
                thread[i].offer(0);//조합 만큼 스레드 대기
                
            }
            
        }
        
        for(int[] req : reqs){
            
            int arrivalTime = req[0];
            int runningTime = req[1];
            int type = req[2]-1;
            
            int endTime = thread[type].peek();
            
            if(endTime <= arrivalTime){
                
                thread[type].poll();
                thread[type].offer(arrivalTime + runningTime);
                
            }else{
                thread[type].poll();
                time += endTime-arrivalTime;
                    
                // if(answer != Integer.MAX_VALUE && answer <= time){
                //     return;
                // }
                // System.out.println("time = " + time);
                thread[type].offer(endTime + runningTime);
                // System.out.println("threadTable = " + threadTable);
            }
        }
        
        answer = Math.min(answer, time);
        
    }
}