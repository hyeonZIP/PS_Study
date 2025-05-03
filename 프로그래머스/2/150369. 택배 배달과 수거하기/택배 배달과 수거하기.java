import java.io.*;
import java.util.*;

class Solution {
    
    public int lastDeliveryIndex;
    public int lastPickupIndex;
    public int[] delivery;
    public int[] pickup;
    
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        
        long answer = 0;
        
        int sum1 = 0;
        int sum2 = 0;
        
        for(int i=n-1; i>=0; i--){
            
            sum1 += deliveries[i];
            sum2 += pickups[i];
            
            while( sum1 > 0 || sum2 > 0){
                
                sum1 -= cap;
                sum2 -= cap;
                answer += (i+1) * 2;
                
            }
            
        }
   
        return answer;
    }
}