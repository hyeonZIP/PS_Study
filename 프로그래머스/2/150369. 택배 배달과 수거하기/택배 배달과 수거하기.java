import java.io.*;
import java.util.*;

class Solution {
    
    public int lastDeliveryIndex;
    public int lastPickupIndex;
    public int[] delivery;
    public int[] pickup;
    
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        
        long answer = 0L;
        
        int index = n;
        
        while(n!=-1){
            
            int sum1 = 0;
            for(int i=index-1; i>=0; i--){
                
                sum1 += deliveries[i];
                
                
                
            }

        }
   
        return answer;
    }
}