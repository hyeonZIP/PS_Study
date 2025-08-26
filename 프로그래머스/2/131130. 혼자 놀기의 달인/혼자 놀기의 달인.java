import java.io.*;
import java.util.*;

/*

cards에서 임의의 상자를 열어서 게임 시작
해당 카드 번호의 상자를 연다
이미 열려있는 경우 1번 그룹

또 반복해서 열려있는 경우일 때까지 반복한 2번 그룹

완탐?

그룹이 되는 숫자는 어느걸 선택해도 동일한 그룹이 되니까 한번의 탐색에서 그 이후 또 탐색이 안되도록 해야한다
*/

class Solution {
    
    public int solution(int[] cards) {
        
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(o->-o));
        
        int len = cards.length;
        int answer = 0;
        
        for(int i=0; i<len; i++){
            
            int count = 0;
            
            while(cards[i]!=0){
                
                
                count++;
                int nextIndex = cards[i]-1;
                cards[i] = 0;
                i = nextIndex;
            }
            
            pq.offer(count);
        }
        
        if(pq.size() < 2){
            
            return 0;
        }

        answer = pq.poll() * pq.poll();
        
        return answer;
    }
}