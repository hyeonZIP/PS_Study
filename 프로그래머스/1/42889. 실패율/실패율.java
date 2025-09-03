import java.io.*;
import java.util.*;

class Solution {
    public int[] solution(int N, int[] stages) {
        
        Arrays.sort(stages);
        
        HashMap<Integer, Float> map = new HashMap<>();
        
        for(int i=0; i<N; i++){
            
            map.put(i+1,0f);
        }

        int len = stages.length;
        
        int previous = stages[len-1];
        
        for(int i=len-1, reachCount = 0, unclearCount = 0; i>=0; i--){
            
            if(stages[i] == N+1){
                reachCount++;
                continue;  
            }
            
            if(previous == stages[i]){
                
                unclearCount++;
                reachCount++;
            }
            
            if(previous != stages[i]){
                
                if(previous != N+1){
                    
                    map.put(previous, (float)unclearCount / reachCount);
                }
                
                previous = stages[i];
                
                unclearCount = 1;
                reachCount++;
                
            }
            if(i-1 < 0){
                
                map.put(stages[i], (float)unclearCount / reachCount);
            }
        }
        
        List<Map.Entry<Integer,Float>> answer = new ArrayList<>(map.entrySet());
        
        answer.sort((o1,o2)->{
            
            if(o1.getValue().equals(o2.getValue())){
                
                return o1.getKey() - o2.getKey();
            }
            
            return Float.compare(o2.getValue(), o1.getValue());
        });
        
        return answer.stream().mapToInt(i->i.getKey()).toArray();
    }
}